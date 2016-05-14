package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.Contributor;

public interface ContributorMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Contributor record);

    Contributor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contributor record);

}