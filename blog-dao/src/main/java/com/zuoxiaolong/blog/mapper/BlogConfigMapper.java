package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.persistent.BlogConfig;


public interface BlogConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BlogConfig record);

    BlogConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogConfig record);

    BlogConfig selectByWebUserId(Integer webUserId);

    int updateByWebUserId(BlogConfig blogConfig);
}