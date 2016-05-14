package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.MessageBox;

public interface MessageBoxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageBox record);

    int insertSelective(MessageBox record);

    MessageBox selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageBox record);

    int updateByPrimaryKey(MessageBox record);
}