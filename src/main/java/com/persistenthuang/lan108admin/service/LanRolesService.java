package com.persistenthuang.lan108admin.service;

import com.persistenthuang.lan108admin.pojo.LanRoles;

import java.util.List;

public interface LanRolesService {
    LanRoles getRolesMessageByID(int RolesId);

    LanRoles getViewRolesMessageByID(int RolesId);

    List<LanRoles> getAllList();

    List<LanRoles> getAllRolesName();

    int UpdateLanRoleByRoleId(LanRoles role);
}
