package com.persistenthuang.lan108admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.persistenthuang.lan108admin.pojo.LanBlogs;
import com.persistenthuang.lan108admin.pojo.LanBlogsTemp;
import com.persistenthuang.lan108admin.pojo.LanMassage;

import java.util.List;

public interface LanBlogsService {
    LanBlogs getBlogAllMessageByID(int id);

    List<LanBlogs> selectBlogPage(Page<LanBlogs> page);

    List<LanBlogs> selectBlogIndexFirst();

    List<LanBlogs> SearchListByKeywords(String keywords);

    List<LanBlogs> getBlogsByRoleID(int roleId);

    List<LanBlogs> getBlogsByAuthorID(int authorID);

    List<LanBlogs> getFocusBlogsByMsg(List<LanMassage> massage);

    int insertLanBlogsByTemp(LanBlogsTemp lanBlogsTemp);

    int getBlogRoleIdByBlogId(int BlogId);
}
