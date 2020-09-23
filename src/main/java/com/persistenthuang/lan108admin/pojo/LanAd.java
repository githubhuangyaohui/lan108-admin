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
@TableName("lan_ad")
public class LanAd implements Serializable {
    @TableId(type = IdType.AUTO)
    private int id;

    @TableField(value = "lan_ad_image")
    private String lanAdImage;                        //广告图片链接

    @TableField(value = "lan_ad_link")
    private String lanAdLink;                        //广告跳转链接
}
