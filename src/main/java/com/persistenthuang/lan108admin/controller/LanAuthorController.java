package com.persistenthuang.lan108admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.persistenthuang.lan108admin.Result.ResuleMessage;
import com.persistenthuang.lan108admin.Result.Result;
import com.persistenthuang.lan108admin.pojo.LanAuthor;
import com.persistenthuang.lan108admin.pojo.User;
import com.persistenthuang.lan108admin.service.LanAuthorServiceImpl;
import com.persistenthuang.lan108admin.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class LanAuthorController {
    @Autowired
    LanAuthorServiceImpl lanAuthorService;

    @Autowired
    UserServiceImpl userService;

    /**
     * 根据用户Id获取作者id
     *
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @RequestMapping("/api/author/getAuthorID")
    @ResponseBody
    public String getAuthorID(@RequestBody JSONObject jsonObject) {
        int userId = jsonObject.getInteger("userId");
        int authorID = userService.getAuthorID(userId);
        log.info("返回作者ID:" + authorID);
        return JSONObject.toJSONString(authorID);
    }

    /**
     * 根据作者ID获取用户全部信息
     *
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @RequestMapping("/api/view/getAuthorMessage")
    @ResponseBody
    public String getAuthorMessage(@RequestBody JSONObject jsonObject) {
        int authorID = jsonObject.getInteger("authorID");
        LanAuthor authorMessage = lanAuthorService.getAllAuthorMessageById(authorID);
        return JSONObject.toJSONString(authorMessage);
    }

    /**
     * 根据userId获取作者信息
     *
     * @param userId
     * @return
     */
    @CrossOrigin
    @RequestMapping("/api/admin/{userId}/author")
    @ResponseBody
    public String CreateList(@PathVariable(value = "userId") int userId) {
        int authorID = userService.getAuthorID(userId);
        LanAuthor author = lanAuthorService.getByAuthorID(authorID);
        log.info("返回作者信息:" + JSONObject.toJSONString(author));
        return JSONObject.toJSONString(author);
    }

    /**
     * 根据作者id获取作者全部信息
     *
     * @param id
     * @return
     */
    @CrossOrigin
    @RequestMapping("/api/view/{id}/authorMassage")
    @ResponseBody
    public String getAuthorMassage(@PathVariable(value = "id") int id) {
        LanAuthor author = lanAuthorService.getAllAuthorMessageById(id);
        log.info("获取作者信息:" + author.toString());
        return JSONObject.toJSONString(author);
    }

    /**
     * 注册成为作者
     *
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @RequestMapping("/api/admin/addAuthor")
    @ResponseBody
    public String AddAuthor(@RequestBody JSONObject jsonObject) {
        log.info("注册成为作者");
        int userId = jsonObject.getInteger("userId");
        int authorID = userService.getAuthorID(userId);

        LanAuthor lanAuthor = new LanAuthor();
        lanAuthor.setAuthorShowName(jsonObject.getString("authorShowName"));
        lanAuthor.setAuthorPhone(jsonObject.getString("authorPhone"));
        lanAuthor.setAuthorEmail(jsonObject.getString("authorEmail"));

        if (lanAuthor.getAuthorShowName().equals("")) {
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "不能为空"));
        }
        if (lanAuthor.getAuthorEmail().equals("")) {
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "不能为空"));
        }
        if (lanAuthor.getAuthorPhone().equals("")) {
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "不能为空"));
        }

        if (authorID == 0) {
            int changeId = lanAuthorService.addAuthor(lanAuthor);
            User user = new User();
            user.setId(userId);
            user.setUserAuthorId(changeId);
            userService.updateAuthor(user);
            return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, "注册成功"));
        } else {
            lanAuthor.setId(authorID);
            lanAuthorService.updateAuthor(lanAuthor);
            return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, "修改成功"));
        }
    }
}
