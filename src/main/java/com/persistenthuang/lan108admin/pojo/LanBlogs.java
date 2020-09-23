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
@TableName("lan_blogs")
public class LanBlogs implements Serializable {
    @TableId(type = IdType.AUTO)
    private int id;

    @TableField(value = "blogs_author_id")
    private int blogsAuthorId;              //作者ID

    @TableField(value = "blogs_create_data", fill = FieldFill.INSERT)
    private LocalDateTime blogsCreateData;  //创建时间

    @TableField(value = "blogs_title")
    private String blogsTitle;              //标题

    @TableField(value = "blogs_cover")
    private String blogsCover;              //标题

    @TableField(value = "blogs_summary")
    private String blogsSummary;              //摘要

    @TableField(value = "blogs_html")
    private String blogsHtml;              //html

    @TableField(value = "blogs_content")
    private String blogsContent;            //内容

    @TableField(value = "blogs_column")
    private int blogsColumn;             //分类

    @TableField(value = "blogs_password", fill = FieldFill.INSERT)
    private String blogsPassword;           //密码

    @TableField(value = "blogs_status", fill = FieldFill.INSERT)
    private int blogsStatus;            //状态：0代表未发表，1代表发表
}
