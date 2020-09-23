package com.persistenthuang.lan108admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.persistenthuang.lan108admin.Result.ResuleMessage;
import com.persistenthuang.lan108admin.Result.Result;
import com.persistenthuang.lan108admin.pojo.User;
import com.persistenthuang.lan108admin.service.LanAdminServiceImpl;
import com.persistenthuang.lan108admin.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理员控制类
 */
@Slf4j
@Controller
public class LanAdminController {
    @Autowired
    LanAdminServiceImpl lanAdminService;

    @Autowired
    UserServiceImpl userService;

    /**
     * 获取专栏管理员名称
     *
     * @param jsonObject RoleId
     * @return 管理员name
     */
    @CrossOrigin
    @RequestMapping("/api/lan/rolesAdminName")
    @ResponseBody
    public String CreateList(@RequestBody JSONObject jsonObject) {
        int roleID = jsonObject.getInteger("roleID");
        if (lanAdminService.isExitByRoleId(roleID)) {
            int userID = lanAdminService.getUserIdByRoleId(roleID);
            if (userService.isExistByID(userID)) {
                User user = userService.getUserById(userID);
                return JSONObject.toJSONString(user.getUserName());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 判断用户是否是管理员
     *
     * @param jsonObject userId
     * @return 是返回200，不是返回400
     */
    @CrossOrigin
    @RequestMapping("/api/admin/isAdmin")
    @ResponseBody
    public String isAdmin(@RequestBody JSONObject jsonObject) {
        int userId = jsonObject.getInteger("userId");
        if (lanAdminService.isExitByUserId(userId)) {
            return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, ""));
        } else {
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, ""));
        }
    }

}
