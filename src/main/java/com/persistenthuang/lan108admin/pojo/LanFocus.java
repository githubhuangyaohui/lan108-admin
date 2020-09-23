package com.persistenthuang.lan108admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("lan_focus")
public class LanFocus implements Serializable {
    @TableId(type = IdType.AUTO)
    private int id;

    @TableField(value = "user_id")
    private int userId;

    @TableField(value = "role_id")
    private int roleId;
}
