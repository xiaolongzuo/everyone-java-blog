package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.persistent.WebUser;

public interface WebUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(WebUser record);

    WebUser selectByPrimaryKey(Integer id);

    WebUser selectByWebUser(WebUser webUser);

    int updateByPrimaryKeySelective(WebUser record);

}