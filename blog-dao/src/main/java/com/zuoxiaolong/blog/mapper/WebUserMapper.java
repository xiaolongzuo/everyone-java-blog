package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.WebUser;

public interface WebUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebUser record);

    int insertSelective(WebUser record);

    WebUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebUser record);

    int updateByPrimaryKey(WebUser record);
}