package com.persistenthuang.lan108admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.persistenthuang.lan108admin.pojo.LanAd;
import com.persistenthuang.lan108admin.service.LanAdServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class LanAdController {

    @Autowired
    LanAdServiceImpl lanAdService;

    @CrossOrigin
    @PostMapping("/api/home/index/image")
    @ResponseBody
    public String getHomeImage() {
        List<LanAd> lanAdList = lanAdService.getLanAdList();
        return JSONObject.toJSONString(lanAdList);
    }
}
