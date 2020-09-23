package com.persistenthuang.lan108admin.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private int id;

    @TableField(value = "user_author_id", fill = FieldFill.INSERT)
    private int userAuthorId;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "user_password")
    private String userPassword;
    @TableField(value = "user_salt")
    private String userSalt;

    @TableField(value = "user_sex")
    private boolean userSex;

    @TableField(value = "user_email")
    private String userEmail;

    @TableField(value = "user_level", fill = FieldFill.INSERT)
    private int userLevel;

    @TableField(value = "user_role", fill = FieldFill.INSERT)
    private int userRole;

    @TableField(value = "user_register_time", fill = FieldFill.INSERT)
    private LocalDateTime userRegisterTime;

    @TableField(value = "user_is_delete", fill = FieldFill.INSERT)
    private boolean userIsDelete;
}
