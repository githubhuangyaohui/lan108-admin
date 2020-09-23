package com.persistenthuang.lan108admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.persistenthuang.lan108admin.Result.ResuleMessage;
import com.persistenthuang.lan108admin.Result.Result;
import com.persistenthuang.lan108admin.pojo.LanBlogsTemp;
import com.persistenthuang.lan108admin.pojo.LanFocus;
import com.persistenthuang.lan108admin.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 草稿控制
 */
@Slf4j
@Controller
public class LanBlogsTempController {
    @Autowired
    LanBlogsTempServiceImpl lanBlogsTempService;

    @Autowired
    LanAdminServiceImpl lanAdminService;

    @Autowired
    LanBlogsServiceImpl lanBlogsService;

    @Autowired
    LanMassageServiceImpl lanMassageService;

    @Autowired
    LanFocusServiceImpl lanFocusService;

    @Autowired
    UserServiceImpl userService;

    /**
     * 根据作者id返回作者草稿
     *
     * @param jsonObject 作者ID
     * @return 草稿文章列表
     */
    @CrossOrigin
    @RequestMapping("/api/blogTemp/getTempBlog")
    @ResponseBody
    public String CreateList(@RequestBody JSONObject jsonObject) {
        int userAuthorId = jsonObject.getInteger("userAuthorId");
        List<LanBlogsTemp> lanBlogsTemps = lanBlogsTempService.CreatListByAuthorID(userAuthorId);
        log.info("创建博客草稿列表" + lanBlogsTemps.toString());
        return JSONObject.toJSONString(lanBlogsTemps);
    }

    /**
     * 根据作者id和文章id删除文章
     *
     * @param jsonObject
     * @return 是否成功
     */
    @CrossOrigin
    @RequestMapping("/api/blogTemp/delete")
    @ResponseBody
    public String blogTempDelete(@RequestBody JSONObject jsonObject) {
        //获取前端信息
        int userId = jsonObject.getInteger("userId");
        int blogTempId = jsonObject.getInteger("blogTempId");
        //获取作者id
        int authorId = userService.getAuthorID(userId);
        //构建参数
        LanBlogsTemp lanBlogsTemp = new LanBlogsTemp();
        lanBlogsTemp.setId(blogTempId);
        lanBlogsTemp.setBlogsAuthorId(authorId);
        //删除文章
        int num = lanBlogsTempService.DeleteBlogsTemp(lanBlogsTemp);
        if (num == 0) {
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "删除失败"));
        } else {
            return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, "删除成功"));
        }
    }

    /**
     * 根据作者id返回作者已投稿
     *
     * @param jsonObject 作者ID
     * @return 草稿文章列表
     */
    @CrossOrigin
    @RequestMapping("/api/blogTemp/getTempBlog/Submit")
    @ResponseBody
    public String CreateListSubmit(@RequestBody JSONObject jsonObject) {
        int userAuthorId = jsonObject.getInteger("userAuthorId");
        List<LanBlogsTemp> lanBlogsTemps = lanBlogsTempService.CreatListSubmitByAuthorID(userAuthorId);
        log.info("创建博客已提交列表" + lanBlogsTemps.toString());
        return JSONObject.toJSONString(lanBlogsTemps);
    }

    /**
     * 根据作者id和文章id撤销投稿
     *
     * @param jsonObject
     * @return 是否成功
     */
    @CrossOrigin
    @RequestMapping("/api/blogTemp/unSubmit")
    @ResponseBody
    public String blogTempUnSubmit(@RequestBody JSONObject jsonObject) {
        //获取前端信息
        int userId = jsonObject.getInteger("userId");
        int blogTempId = jsonObject.getInteger("blogTempId");
        //获取作者id
        int authorId = userService.getAuthorID(userId);
        //构建参数
        LanBlogsTemp lanBlogsTemp = new LanBlogsTemp();
        lanBlogsTemp.setId(blogTempId);
        lanBlogsTemp.setBlogsAuthorId(authorId);
        int i = lanBlogsTempService.UpdateBlogsTempToTemp(lanBlogsTemp);
        if (i == 0) {
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "撤销失败"));
        } else {
            return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, "撤销成功"));

        }
    }

    /**
     * 投稿文章
     *
     * @param jsonObject Json数据
     * @return 成功与否
     */
    @CrossOrigin
    @RequestMapping("/api/creat/publishedArticle")
    @ResponseBody
    public String publishedArticle(@RequestBody JSONObject jsonObject) {
        int blogTempId = jsonObject.getInteger("blogId");
        log.info("发布文章ID:" + blogTempId);
        //更改博客状态
        lanBlogsTempService.UpdateBlogsTempStatusToPublishByID(blogTempId);
        return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, "投稿成功"));
    }

    /**
     * 保存草稿
     *
     * @param jsonObject
     * @return 返回保存草稿文章id
     */
    @CrossOrigin
    @RequestMapping("/api/creat/saveArticle")
    @ResponseBody
    public String SaveBlog(@RequestBody JSONObject jsonObject) {
        log.info("************保存博客************");
        log.info("文章前端传回字符串:" + jsonObject.toJSONString());
        int id = jsonObject.getInteger("id");
        int blogsAuthorId = jsonObject.getInteger("blogsAuthorId");
        String blogsTitle = jsonObject.getString("blogsTitle");
        String blogsContent = jsonObject.getString("blogsContent");
        String blogsHtml = jsonObject.getString("blogsHtml");
        String blogsSummary = jsonObject.getString("blogsSummary");
        String blogsCover = jsonObject.getString("blogsCover");
        int blogsColumn = jsonObject.getInteger("blogsColumn");

        LanBlogsTemp lanBlogsTemp = new LanBlogsTemp();
        lanBlogsTemp.setBlogsAuthorId(blogsAuthorId);
        lanBlogsTemp.setBlogsTitle(blogsTitle);
        lanBlogsTemp.setBlogsContent(blogsContent);
        lanBlogsTemp.setBlogsHtml(blogsHtml);
        lanBlogsTemp.setBlogsCover(blogsCover);
        lanBlogsTemp.setBlogsSummary(blogsSummary);
        lanBlogsTemp.setBlogsColumn(blogsColumn);
        if (id == 0) {
            log.info("新建博客" + lanBlogsTemp.toString());
            lanBlogsTempService.InsertBlogsTemp(lanBlogsTemp);
        } else {
            log.info("修改博客");
            lanBlogsTemp.setId(id);
            lanBlogsTempService.UpdateBlogsTemp(lanBlogsTemp);
        }
        return JSONObject.toJSONString(lanBlogsTemp.getId());
    }

    /**
     * 管理员返回提交的博客列表
     *
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @RequestMapping("/api/admin/submitBlogs")
    @ResponseBody
    public String getsubmitBlogs(@RequestBody JSONObject jsonObject) {
        int userID = jsonObject.getInteger("userID");
        int roleId = lanAdminService.getRoleIdByUserId(userID);
        List<LanBlogsTemp> submitBlogs = lanBlogsTempService.getSubmitBlogsByRoleId(roleId);
        log.info("投稿的专栏信息" + submitBlogs.toString());
        return JSONObject.toJSONString(submitBlogs);
    }

    /**
     * 管理员返回通过的专栏列表
     *
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @RequestMapping("/api/admin/passBlogs")
    @ResponseBody
    public String getPassedBlogList(@RequestBody JSONObject jsonObject) {
        int userID = jsonObject.getInteger("userID");
        int roleID = lanAdminService.getRoleIdByUserId(userID);
        List<LanBlogsTemp> passBlogs = lanBlogsTempService.getPassBlogsByRoleId(roleID);
        log.info("通过文章列表" + passBlogs.toString());
        return JSONObject.toJSONString(passBlogs);
    }

    /**
     * 管理员返回不通过的列表
     *
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @RequestMapping("/api/admin/unPassBlogs")
    @ResponseBody
    public String getUnPassedBlogList(@RequestBody JSONObject jsonObject) {
        int userID = jsonObject.getInteger("userID");
        int roleId = lanAdminService.getRoleIdByUserId(userID);
        List<LanBlogsTemp> unPassBlogs = lanBlogsTempService.getUnPassBlogsByRoleId(roleId);
        log.info("没有通过的博客列表:" + unPassBlogs.toString());
        return JSONObject.toJSONString(unPassBlogs);
    }

    /**
     * 预览blogsTemp信息
     *
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @RequestMapping("/api/view/blogTemp")
    @ResponseBody
    public String getViewBlogsMessage(@RequestBody JSONObject jsonObject) {
        int id = jsonObject.getInteger("id");
        LanBlogsTemp lanBlogsTemp = lanBlogsTempService.getAllViewMessageByID(id);
        log.info("预览博客信息:" + lanBlogsTemp.toString());
        return JSONObject.toJSONString(lanBlogsTemp);
    }

    /**
     * 管理员通过博客，并发布到专栏
     *
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @RequestMapping("/api/admin/publicBlogs")
    @ResponseBody
    public String publicBlogs(@RequestBody JSONObject jsonObject) {
        int userId = jsonObject.getInteger("userID");
        int blogsTempId = jsonObject.getInteger("blogTempId");
        int roleID = lanAdminService.getRoleIdByUserId(userId);
        if (roleID != 0) {
            LanBlogsTemp lanBlogsTemp = lanBlogsTempService.setPassBlogsByTempId(blogsTempId);
            int blogID = lanBlogsService.insertLanBlogsByTemp(lanBlogsTemp);
            log.info("提交通过请求，发布文章内容：" + lanBlogsTemp.toString());
            if (blogID != 0) {
                int roleId = lanBlogsService.getBlogRoleIdByBlogId(blogID);
                List<LanFocus> focusUser = lanFocusService.getFocusUserByRoleId(roleId);
                for (int i = 0; i < focusUser.size(); i++) {
                    int focusUserId = focusUser.get(i).getUserId();
                    log.info("推送关注消息:" + blogID + focusUserId);
                    lanMassageService.sendMessage(focusUserId, blogID);
                }
                return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, "发布成功"));
            } else {
                return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "发布失败"));
            }
        } else {
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "发布失败"));
        }
    }

    /**
     * 提交不通过请求
     *
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @RequestMapping("/api/admin/unpassBlogs")
    @ResponseBody
    public String UnpassBlogs(@RequestBody JSONObject jsonObject) {
        int userId = jsonObject.getInteger("userID");
        int blogsTempId = jsonObject.getInteger("blogTempId");
        int roleID = lanAdminService.getRoleIdByUserId(userId);
        if (roleID != 0) {
            lanBlogsTempService.setUnPassBlogsBytempId(blogsTempId);
            log.info("提交不通过请求：成功");
            return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, "提交不通过请求：成功"));
        } else {
            log.info("提交不通过请求：失败");
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "提交不通过请求：失败"));
        }
    }
}
