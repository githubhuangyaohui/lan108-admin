package com.persistenthuang.lan108admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.persistenthuang.lan108admin.Result.ResuleMessage;
import com.persistenthuang.lan108admin.Result.Result;
import com.persistenthuang.lan108admin.service.LanFocusServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 关注信息控制类
 */
@Slf4j
@Controller
public class LanFocusController {
    @Autowired
    LanFocusServiceImpl lanFocusService;

    /**
     * 返回是否关注
     *
     * @param jsonObject userID+roleID
     * @return 是否关注
     */
    @CrossOrigin
    @RequestMapping("/api/lan/rolesFocus")
    @ResponseBody
    public String isFocus(@RequestBody JSONObject jsonObject) {
        int roleID = jsonObject.getInteger("roleId");
        int userID = jsonObject.getInteger("userID");
        if (lanFocusService.isFocus(userID, roleID)) {
            log.info("判断已经关注：" + userID + "+" + roleID);
            return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, ""));
        } else {
            log.info("判断没有关注：" + userID + "+" + roleID);
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, ""));
        }
    }

    /**
     * 关注专栏
     *
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @RequestMapping("/api/lan/getFocus")
    @ResponseBody
    public String getFocus(@RequestBody JSONObject jsonObject) {
        int roleID = jsonObject.getInteger("roleId");
        int userID = jsonObject.getInteger("userID");
        log.info("获得关注：" + userID + "+" + roleID);
        if (lanFocusService.isFocus(userID, roleID)) {
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "已关注"));
        } else {
            lanFocusService.getFocus(userID, roleID);
            return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, "关注成功"));
        }
    }

    /**
     * 取消关注专栏
     *
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @RequestMapping("/api/lan/getUnFocus")
    @ResponseBody
    public String getUnFocus(@RequestBody JSONObject jsonObject) {
        int roleID = jsonObject.getInteger("roleId");
        int userID = jsonObject.getInteger("userID");
        log.info("取消关注：" + userID + "+" + roleID);
        if (lanFocusService.isFocus(userID, roleID)) {
            lanFocusService.getUnnFocus(userID, roleID);
            return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, "取消关注成功"));
        } else {
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "已取消关注"));
        }
    }
}
