package com.persistenthuang.lan108admin.service;

import com.persistenthuang.lan108admin.pojo.LanBlogsTemp;

import java.util.List;

public interface LanBlogsTempService {
    List<LanBlogsTemp> CreatListByAuthorID(int id);

    List<LanBlogsTemp> CreatListSubmitByAuthorID(int id);

    int UpdateBlogsTemp(LanBlogsTemp lanBlogsTemp);

    int UpdateBlogsTempToTemp(LanBlogsTemp lanBlogsTemp);

    int InsertBlogsTemp(LanBlogsTemp lanBlogsTemp);

    int DeleteBlogsTemp(LanBlogsTemp lanBlogsTemp);

    int UpdateBlogsTempStatusToPublishByID(int id);

    List<LanBlogsTemp> getSubmitBlogsByRoleId(int RoleID);

    List<LanBlogsTemp> getPassBlogsByRoleId(int RoleID);

    List<LanBlogsTemp> getUnPassBlogsByRoleId(int RoleID);

    LanBlogsTemp getAllViewMessageByID(int id);

    LanBlogsTemp getAllEditorMessageByID(int id);

    LanBlogsTemp setPassBlogsByTempId(int tempId);

    int setUnPassBlogsBytempId(int tempId);
}
