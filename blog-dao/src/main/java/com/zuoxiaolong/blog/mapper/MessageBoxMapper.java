package com.zuoxiaolong.blog.mapper;

import com.zuoxiaolong.blog.common.orm.DigitalPage;
import com.zuoxiaolong.blog.model.persistent.MessageBox;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageBoxMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MessageBox record);

    MessageBox selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageBox record);

    List<MessageBox> getMessagesByPage(@Param("page") DigitalPage page,
                                       @Param("messageBox") MessageBox messageBox);
}