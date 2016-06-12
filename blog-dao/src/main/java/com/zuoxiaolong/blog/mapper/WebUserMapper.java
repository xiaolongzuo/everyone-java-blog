package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.common.orm.DigitalPage;
import com.zuoxiaolong.blog.common.orm.DropDownPage;
import com.zuoxiaolong.blog.model.persistent.WebUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 返回值为查出来的分页数据,而且返回结果同时会被自动装配到page对象中.
     * 拦截器默认的排序方式为ORDER BY id DESC排序.
     * 支持自定义排序,只要给page对象设置orderColumn和orderType字段,即可改变排序方式.
     * NOTE:强烈建议使用默认的id字段进行排序,除非你有足够的理由,才能使用自定义排序.
     *
     * @param page
     * @param param
     * @return
     */
    List<WebUser> testDigitalPage(@Param("page") DigitalPage page, @Param("param") WebUser param);

    /**
     * 这个是没有其它参数时的写法
     *
     * @param page
     * @return
     */
    List<WebUser> testDigitalPage2(@Param("page") DigitalPage page);

    /**
     * 测试分页,后面会删掉
     * 必须有一个被@Param注解的参数,参数名必须为page,类型为DropDownPage,即下拉分页的意思.
     * 返回值为查出来的分页数据,而且返回结果同时会被自动装配到page对象中.
     * 此外,在下拉分页时,框架会将末尾的offset自动填充到page对象中,前端可以直接传递offset来进行下一次查询.
     * 拦截器默认的排序方式为ORDER BY id DESC排序.
     * 支持自定义排序,只要给page对象设置orderColumn和orderType字段,即可改变排序方式.
     * NOTE1:强烈建议使用默认的id字段进行排序,除非你有足够的理由,才能使用自定义排序.
     * NOTE2:在使用下拉分页时,改变排序字段也同时会改变offset的判断字段.
     *
     * @param page
     * @param param
     * @return
     */
    List<WebUser> testDropDownPage(@Param("page") DropDownPage page, @Param("param") WebUser param);

    /**
     * 这个是没有其它参数时的写法
     *
     * @param page
     * @return
     */
    List<WebUser> testDropDownPage2(@Param("page") DropDownPage page);

}