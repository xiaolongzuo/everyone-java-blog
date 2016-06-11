package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.common.mybatis.DigitalPage;
import com.zuoxiaolong.blog.common.mybatis.DropDownPage;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import org.apache.ibatis.annotations.Param;

public interface WebUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(WebUser record);

    WebUser selectByPrimaryKey(Integer id);

    WebUser selectByWebUser(WebUser webUser);

    int updateByPrimaryKeySelective(WebUser record);

    WebUser selectByUsername(String username);

    /**
     * 测试分页,后面会删掉
     * 必须有一个被@Param注解的参数,参数名必须为page,类型为DigitalPage,即数字分页的含义.
     * 返回值不需要有,返回结果会被自动装配到page对象中.
     *
     * @param page
     * @param param
     * @return
     */
    void testDigitalPage(@Param("page") DigitalPage page, @Param("param") WebUser param);

    /**
     * 测试分页,后面会删掉
     * 必须有一个被@Param注解的参数,参数名必须为page,类型为DropDownPage,即下拉分页的意思.
     * 返回值不需要有,返回结果会被自动装配到page对象中.
     *
     * @param page
     * @param param
     * @return
     */
    void testDropDownPage(@Param("page") DropDownPage page, @Param("param") WebUser param);

}