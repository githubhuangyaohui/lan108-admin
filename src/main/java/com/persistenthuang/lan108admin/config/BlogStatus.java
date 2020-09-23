package com.persistenthuang.lan108admin.config;

/**
 * 文章状态
 */
public class BlogStatus {
    //temp使用
    static public final int TEMP = 0;     //文章状态:草稿
    static public final int REVIEW = 1;   //文章状态:审核
    static public final int SUCCESS = 2;     //文章状态:通过
    static public final int FAIL = 3;     //文章状态:不通过

    //通过文章使用
    static public final int SIMPLE = 0;     //文章类型:普通
    static public final int FIRST = 1;     //文章类型:置顶
}
