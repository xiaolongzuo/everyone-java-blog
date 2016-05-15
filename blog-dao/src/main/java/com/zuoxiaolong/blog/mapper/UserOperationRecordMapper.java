package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.persistent.UserOperationRecord;

public interface UserOperationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserOperationRecord record);

    UserOperationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOperationRecord record);

}