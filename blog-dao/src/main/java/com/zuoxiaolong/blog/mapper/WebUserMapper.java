package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.persistent.WebUser;
import org.springframework.stereotype.Repository;

public interface WebUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(WebUser record);

    WebUser selectByWebUserId(int webUserId);

    WebUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebUser record);

}