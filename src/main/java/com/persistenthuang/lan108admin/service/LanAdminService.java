package com.persistenthuang.lan108admin.service;

public interface LanAdminService {
    int getUserIdByRoleId(int roleId);

    boolean isExitByRoleId(int roleId);

    boolean isExitByUserId(int userId);

    int getRoleIdByUserId(int userID);

}
