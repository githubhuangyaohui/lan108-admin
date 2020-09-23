package com.persistenthuang.lan108admin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheatMassage {
    //用户id
    private Integer userId;
    //用户名
    private String userName;
    //用户信息
    private String msg;
    //在线人数
    private int count;
}
