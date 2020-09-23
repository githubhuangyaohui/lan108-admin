package com.persistenthuang.lan108admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("lan_admin")
public class LanAdmin implements Serializable {
    @TableId(type = IdType.AUTO)
    private int id;

    @TableField(value = "user_id")
    private int userId;                        //用户id

    @TableField(value = "role_id")
    private int roleId;                        //用户角色ID

    @TableField(value = "admin_creat_data")
    private LocalDateTime adminCreatData;     //创建时间

    @TableField(value = "admin_is_delete")
    private boolean adminIsDelete;            //是否删除
}
