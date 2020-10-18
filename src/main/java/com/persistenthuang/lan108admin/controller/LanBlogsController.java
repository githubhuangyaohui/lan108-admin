package com.persistenthuang.lan108admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.persistenthuang.lan108admin.pojo.LanBlogs;
import com.persistenthuang.lan108admin.service.LanBlogsServiceImpl;
import com.persistenthuang.lan108admin.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 发表文章控制类
 */
@Slf4j
@Controller
public class LanBlogsController {
    @Autowired
    LanBlogsServiceImpl lanBlogsService;

    @Autowired
    UserServiceImpl userService;

    /**
     * 首页推荐文章列表
     *
     * @param currentPage
     * @return JSON字符串
     */
    @CrossOrigin
    @RequestMapping("/api/home/{currentPage}/blogs")
    @ResponseBody
    public String selectBlogIndex(@PathVariable(value = "currentPage") int currentPage) {
        log.info("首页推荐图书");
        Page<LanBlogs> page = new Page<>(currentPage, 10);
        List<LanBlogs> lanBlogs = lanBlogsService.selectBlogPage(page);
        return JSONObject.toJSONString(lanBlogs);
    }

    /**
     * 首页置顶文章文章列表
     *
     * @param
     * @return JSON字符串
     */
    @CrossOrigin
    @RequestMapping("/api/home/blogsFirst")
    @ResponseBody
    public String selectBlogIndexFirst() {
        List<LanBlogs> lanBlogs = lanBlogsService.selectBlogIndexFirst();
        return JSONObject.toJSONString(lanBlogs);
    }

    /**
     * 通过id获取文章详细信息
     *
     * @param id
     * @return JSON字符串
     */
    @CrossOrigin
    @RequestMapping("/api/view/{id}/blogMassage")
    @ResponseBody
    public String getBlogMassage(@PathVariable(value = "id") int id) {
        log.info("获取文章具体信息");
        LanBlogs blog = lanBlogsService.getBlogAllMessageByID(id);
        log.info("获取的文章信息：" + blog.toString());
        return JSONObject.toJSONString(blog);
    }

    /**
     * 通过关键词搜索blog
     *
     * @param jsonObject
     * @return JSON字符串
     */
    @CrossOrigin
    @RequestMapping("/api/search")
    @ResponseBody
    public String selectByKeywords(@RequestBody JSONObject jsonObject) {
        String keywords = jsonObject.getString("key");
        log.info("查找关键词:" + keywords);
        // 关键词为空时查询出所有书籍
        List<LanBlogs> lanBlogs;
        if ("".equals(keywords)) {
            lanBlogs = null;
        } else {
            lanBlogs = lanBlogsService.SearchListByKeywords(keywords);
        }
        return JSONObject.toJSONString(lanBlogs);
    }

    /**
     * 获取专栏文章列表
     *
     * @param rolesID
     * @return JSON字符串
     */
    @CrossOrigin
    @RequestMapping("/api/blogs/rolesBlogs/{rolesID}")
    @ResponseBody
    public String getRoleBlogs(@PathVariable(value = "rolesID") int rolesID) {
        List<LanBlogs> roleBlogs = this.lanBlogsService.getBlogsByRoleID(rolesID);
        log.info("获取专栏文章列表:" + roleBlogs.toString());
        return JSONObject.toJSONString(roleBlogs);
    }

    /**
     * 获取个人中心文章列表
     *
     * @param userID
     * @return JSON字符串
     */
    @CrossOrigin
    @RequestMapping("/api/blogs/adminBlogs/{userID}")
    @ResponseBody
    public String getAdminBlogs(@PathVariable(value = "userID") int userID) {
        int authorID = this.userService.getAuthorID(userID);
        List<LanBlogs> roleBlogs = this.lanBlogsService.getBlogsByAuthorID(authorID);
        log.info("获取个人中心文章列表:" + roleBlogs.toString());
        return JSONObject.toJSONString(roleBlogs);
    }

    /**
     * 获取作者文章列表
     *
     * @param jsonObject
     * @return JSON字符串
     */
    @CrossOrigin
    @RequestMapping("/api/view/getAuthorList")
    @ResponseBody
    public String getAuthorBlogs(@RequestBody JSONObject jsonObject) {
        int authorID = jsonObject.getInteger("authorID");
        List<LanBlogs> roleBlogs = this.lanBlogsService.getBlogsByAuthorID(authorID);
        return JSONObject.toJSONString(roleBlogs);
    }
}
