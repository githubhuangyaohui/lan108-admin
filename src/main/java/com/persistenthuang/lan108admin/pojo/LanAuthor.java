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
@TableName("lan_author")
public class LanAuthor implements Serializable {
    @TableId(type = IdType.AUTO)
    private int id;

    @TableField(value = "author_show_name")
    private String authorShowName;

    @TableField(value = "author_create_data", fill = FieldFill.INSERT)
    private LocalDateTime authorCreateData;

    @TableField(value = "author_email")
    private String authorEmail;

    @TableField(value = "author_phone")
    private String authorPhone;

    @TableField(value = "author_is_delete", fill = FieldFill.INSERT)
    private Boolean authorIsDelete;
}
