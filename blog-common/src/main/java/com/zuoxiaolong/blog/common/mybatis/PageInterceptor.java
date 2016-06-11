/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zuoxiaolong.blog.common.mybatis;

import com.zuoxiaolong.blog.common.utils.CollectionUtils;
import com.zuoxiaolong.blog.common.utils.ObjectUtils;
import com.zuoxiaolong.blog.common.utils.ReflectUtils;
import com.zuoxiaolong.blog.common.utils.StringUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * mybatis分页拦截器实现.
 * 该拦截器可以自动识别需要分页的方法,并且按照一定的规范进行分页.
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})
        , @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class PageInterceptor implements Interceptor {

    private ThreadLocal<Object> dataThreadLocal = new ThreadLocal<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
            StatementHandler delegate = (StatementHandler) ReflectUtils.getFieldValue(statementHandler, "delegate");
            BoundSql boundSql = delegate.getBoundSql();
            Object parameterObject = boundSql.getParameterObject();
            if (!(parameterObject instanceof MapperMethod.ParamMap)) {
                return invocation.proceed();
            }
            MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) parameterObject;
            if (!paramMap.containsKey("page")) {
                return invocation.proceed();
            }
            Object pageObject = paramMap.get("page");
            dataThreadLocal.set(pageObject);
            if (pageObject instanceof DigitalPage) {
                DigitalPage page = (DigitalPage) pageObject;
                MappedStatement mappedStatement = (MappedStatement) ReflectUtils.getFieldValue(delegate, "mappedStatement");
                Connection connection = (Connection) invocation.getArgs()[0];
                String sql = boundSql.getSql();
                setTotalCount(page, parameterObject, mappedStatement, connection);
                ReflectUtils.setFieldValue(boundSql, "sql", getDigitalPageSql(page, sql));
            } else if (pageObject instanceof DropDownPage) {
                DropDownPage page = (DropDownPage) pageObject;
                String sql = boundSql.getSql();
                ReflectUtils.setFieldValue(boundSql, "sql", getDropDownPageSql(page, sql));
            }
            return invocation.proceed();
        } else if (invocation.getTarget() instanceof ResultSetHandler) {
            Object parameterObject = dataThreadLocal.get();
            Object data = invocation.proceed();
            if (parameterObject == null) {
                return data;
            }
            if (!(parameterObject instanceof DigitalPage) && !(parameterObject instanceof DropDownPage)) {
                return data;
            }
            Class<?> clazz = parameterObject.getClass();
            ReflectUtils.setFieldValueWithSetterMethod(parameterObject, data, clazz, clazz.getDeclaredField("data"));
            if (parameterObject instanceof DropDownPage) {
                List<?> dataList = (List<?>) data;
                if (!CollectionUtils.isEmpty(dataList)) {
                    Integer offset = (Integer) ReflectUtils.getFieldValue(dataList.get(dataList.size() - 1), "id");
                    ReflectUtils.setFieldValueWithSetterMethod(parameterObject, offset, clazz, clazz.getDeclaredField("offset"));
                }
            }
            return data;
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else if (target instanceof ResultSetHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private void setTotalCount(DigitalPage page, Object parameterObject, MappedStatement mappedStatement, Connection connection) {
        BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
        String sql = boundSql.getSql();
        String countSql = getTotalCountSql(sql);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, parameterObject);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, countBoundSql);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(countSql);
            parameterHandler.setParameters(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int totalCount = resultSet.getInt(1);
                page.setTotalCount(totalCount);
                page.setTotalPageNumberWithTotalCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String findKeyword(String sql, String keyword) {
        Pattern pattern = Pattern.compile("\\s+" + keyword.toUpperCase() + "\\s+");
        String upperCaseSql = sql.toUpperCase();
        Matcher matcher = pattern.matcher(upperCaseSql);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    private String getTotalCountSql(String sql) {
        String fromKeyword = findKeyword(sql, "from");
        if (!StringUtils.isEmpty(fromKeyword)) {
            int index = sql.toUpperCase().indexOf(fromKeyword);
            return "SELECT COUNT(*) " + sql.substring(index);
        } else {
            throw new RuntimeException("can't find from keyword in page sql.");
        }
    }

    private String getDigitalPageSql(DigitalPage page, String sql) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        int offset = (page.getCurrentPageNumber() - 1) * page.getPageSize();
        String orderKeyword = findKeyword(sql, "order");
        if (StringUtils.isEmpty(orderKeyword)) {
            sqlBuffer.append(" ORDER BY id DESC ");
        }
        sqlBuffer.append(" LIMIT ").append(offset).append(",").append(page.getPageSize());
        return sqlBuffer.toString();
    }

    private String getDropDownPageSql(DropDownPage page, String sql) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        String whereKeyword = findKeyword(sql, "where");
        if (ObjectUtils.isNull(page.getOffset())) {
            sqlBuffer.append(" ORDER BY id DESC LIMIT ").append(page.getSize());
        } else {
            if (!StringUtils.isEmpty(whereKeyword)) {
                sqlBuffer.append(" AND id < ").append(page.getOffset()).append(" ORDER BY id DESC LIMIT ").append(page.getSize());
            } else {
                sqlBuffer.append(" WHERE id < ").append(page.getOffset()).append(" ORDER BY id DESC LIMIT ").append(page.getSize());
            }
        }
        return sqlBuffer.toString();
    }

}
