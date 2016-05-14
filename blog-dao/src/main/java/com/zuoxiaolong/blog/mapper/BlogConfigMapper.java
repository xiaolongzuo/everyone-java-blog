package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.BlogConfig;

public interface BlogConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogConfig record);

    int insertSelective(BlogConfig record);

    BlogConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogConfig record);

    int updateByPrimaryKey(BlogConfig record);
}