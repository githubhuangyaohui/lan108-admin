package com.persistenthuang.lan108admin.service;


import com.persistenthuang.lan108admin.pojo.LanFocus;

import java.util.List;

public interface LanFocusService {
    boolean isFocus(int userID, int roleID);

    int getFocus(int userID, int roleID);

    int getUnnFocus(int userID, int roleID);

    List<LanFocus> getFocusUserByRoleId(int roleId);
}
