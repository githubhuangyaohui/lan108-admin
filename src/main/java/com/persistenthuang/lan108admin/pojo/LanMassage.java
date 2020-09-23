package com.persistenthuang.lan108admin.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("lan_massage")
public class LanMassage {
    @TableId(type = IdType.AUTO)
    private int id;

    @TableField(value = "user_id")
    private int userId;    //用户id

    @TableField(value = "blog_id")
    private int blogId;    //推送id

    @TableField(value = "msg_creat_data", fill = FieldFill.INSERT)
    private LocalDateTime msgCreatData;           //发送时间

    @TableField(value = "msg_type", fill = FieldFill.INSERT)
    private int msgType;           //消息类型

    @TableField(value = "msg_id_read", fill = FieldFill.INSERT)
    private boolean msgIdRead;//是否已阅
}
