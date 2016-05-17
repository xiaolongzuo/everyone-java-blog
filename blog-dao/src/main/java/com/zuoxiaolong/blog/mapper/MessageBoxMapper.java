package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.model.persistent.MessageBox;

import java.util.List;

public interface MessageBoxMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MessageBox record);

    MessageBox selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageBox record);

    List<MessageBox> selectMessageBoxList();
}