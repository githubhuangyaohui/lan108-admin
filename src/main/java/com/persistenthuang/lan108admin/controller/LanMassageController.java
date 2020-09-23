package com.persistenthuang.lan108admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.persistenthuang.lan108admin.Result.ResuleMessage;
import com.persistenthuang.lan108admin.Result.Result;
import com.persistenthuang.lan108admin.pojo.LanMassage;
import com.persistenthuang.lan108admin.service.LanBlogsServiceImpl;
import com.persistenthuang.lan108admin.service.LanMassageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 关注信息控制类
 */
@Slf4j
@Controller
public class LanMassageController {
    @Autowired
    LanBlogsServiceImpl lanBlogsService;

    @Autowired
    LanMassageServiceImpl lanMassageService;

    @CrossOrigin
    @RequestMapping("/api/focus/message/list")
    @ResponseBody
    public String getFocusMassageList(@RequestBody JSONObject jsonObject) {
        //获得用户ID
        int userID = jsonObject.getInteger("userID");
        //获取信息列表
        List<LanMassage> massages = lanMassageService.getMassageListByUserId(userID);
        //返回博客列表
        return JSONObject.toJSONString(lanBlogsService.getFocusBlogsByMsg(massages));
    }

    @CrossOrigin
    @RequestMapping("/api/focus/message/setRead")
    @ResponseBody
    public String setFocusMassageRead(@RequestBody JSONObject jsonObject) {
        //获得用户ID
        int userID = jsonObject.getInteger("userID");
        int blogID = jsonObject.getInteger("blogID");
        //修改数据
        if (lanMassageService.setIsRead(userID, blogID) != 0) {
            return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, "已读"));
        } else {
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "已读失败"));
        }
    }
}
