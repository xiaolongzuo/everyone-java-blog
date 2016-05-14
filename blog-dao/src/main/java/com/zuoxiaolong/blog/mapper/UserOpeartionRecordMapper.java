package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.UserOpeartionRecord;

public interface UserOpeartionRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserOpeartionRecord record);

    int insertSelective(UserOpeartionRecord record);

    UserOpeartionRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOpeartionRecord record);

    int updateByPrimaryKey(UserOpeartionRecord record);
}