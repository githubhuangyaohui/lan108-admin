package com.persistenthuang.lan108admin.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("lan_roles")
public class LanRoles implements Serializable {
    @TableId(type = IdType.AUTO)
    private int id;

    @TableField(value = "roles_lan_name")
    private String rolesLanName;

    @TableField(value = "roles_state", fill = FieldFill.INSERT)
    private int rolesState;

    @TableField(value = "roles_starname")
    private String rolesStarname;

    @TableField(value = "roles_nickname")
    private String rolesNickname;

    @TableField(value = "roles_truename")
    private String rolesTruename;

    @TableField(value = "roles_description")
    private String rolesDescription;

    @TableField(value = "roles_img")
    private String rolesImg;
}
