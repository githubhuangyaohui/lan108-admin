package com.persistenthuang.lan108admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.persistenthuang.lan108admin.config.BlogStatus;
import com.persistenthuang.lan108admin.mapper.LanBlogsTempMapper;
import com.persistenthuang.lan108admin.pojo.LanBlogsTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 草稿文章服务类
 */
@Service
public class LanBlogsTempServiceImpl implements LanBlogsTempService {

    @Autowired
    LanBlogsTempMapper lanBlogsTempMapper;

    /**
     * 根据作者id获取文章信息
     *
     * @param id 作者id
     * @return 草稿文章类
     */
    @Override
    public List<LanBlogsTemp> CreatListByAuthorID(int id) {
        QueryWrapper<LanBlogsTemp> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "blogs_author_id", "blogs_title", "blogs_summary", "blogs_cover", "blogs_content", "blogs_column");
        queryWrapper.
                eq("blogs_author_id", id)
                .eq("blogs_status", BlogStatus.TEMP);
        return lanBlogsTempMapper.selectList(queryWrapper);
    }

    @Override
    public List<LanBlogsTemp> CreatListSubmitByAuthorID(int id) {
        QueryWrapper<LanBlogsTemp> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "blogs_author_id", "blogs_title", "blogs_summary", "blogs_cover", "blogs_content", "blogs_column");
        queryWrapper.
                eq("blogs_author_id", id)
                .eq("blogs_status", BlogStatus.REVIEW);
        return lanBlogsTempMapper.selectList(queryWrapper);
    }

    /**
     * 更新文章
     *
     * @param lanBlogsTemp 更新的文章
     * @return 更新行数
     */
    @Override
    public int UpdateBlogsTemp(LanBlogsTemp lanBlogsTemp) {
        UpdateWrapper<LanBlogsTemp> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", lanBlogsTemp.getId())
                .set("blogs_author_id", lanBlogsTemp.getBlogsAuthorId())
                .set("blogs_title", lanBlogsTemp.getBlogsTitle())
                .set("blogs_summary", lanBlogsTemp.getBlogsSummary())
                .set("blogs_cover", lanBlogsTemp.getBlogsCover())
                .set("blogs_html", lanBlogsTemp.getBlogsHtml())
                .set("blogs_content", lanBlogsTemp.getBlogsContent())
                .set("blogs_column", lanBlogsTemp.getBlogsColumn());
        return lanBlogsTempMapper.update(null, updateWrapper);
    }

    /**
     * 设置博客为未投稿
     *
     * @param lanBlogsTemp
     * @return
     */
    @Override
    public int UpdateBlogsTempToTemp(LanBlogsTemp lanBlogsTemp) {
        UpdateWrapper<LanBlogsTemp> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .eq("id", lanBlogsTemp.getId())
                .eq("blogs_author_id", lanBlogsTemp.getBlogsAuthorId())
                .set("blogs_status", BlogStatus.TEMP);
        return lanBlogsTempMapper.update(null, updateWrapper);
    }

    /**
     * 插入文章
     *
     * @param lanBlogsTemp 插入的数据
     * @return 改变的行数
     */
    @Override
    public int InsertBlogsTemp(LanBlogsTemp lanBlogsTemp) {
        return lanBlogsTempMapper.insert(lanBlogsTemp
        );
    }

    /**
     * 删除博客
     *
     * @param lanBlogsTemp
     * @return
     */
    @Override
    public int DeleteBlogsTemp(LanBlogsTemp lanBlogsTemp) {
        QueryWrapper<LanBlogsTemp> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("id", lanBlogsTemp.getId())
                .eq("blogs_author_id", lanBlogsTemp.getBlogsAuthorId());
        return lanBlogsTempMapper.delete(queryWrapper);
    }

    /**
     * 更新文章的状态为投稿状态
     *
     * @param id ID
     * @return 改变行数
     */
    @Override
    public int UpdateBlogsTempStatusToPublishByID(int id) {
        UpdateWrapper<LanBlogsTemp> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("blogs_status", BlogStatus.REVIEW);
        return lanBlogsTempMapper.update(null, updateWrapper);
    }

    /**
     * 获取投稿文章信息
     *
     * @param RoleID
     * @return
     */
    @Override
    public List<LanBlogsTemp> getSubmitBlogsByRoleId(int RoleID) {
        QueryWrapper<LanBlogsTemp> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "blogs_title", "blogs_cover", "blogs_summary", "blogs_author_id");
        queryWrapper
                .eq("blogs_column", RoleID)
                .eq("blogs_status", BlogStatus.REVIEW);
        return lanBlogsTempMapper.selectList(queryWrapper);
    }

    /**
     * 获取通过文章信息
     *
     * @param RoleID
     * @return
     */
    @Override
    public List<LanBlogsTemp> getPassBlogsByRoleId(int RoleID) {
        QueryWrapper<LanBlogsTemp> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "blogs_title", "blogs_cover", "blogs_summary", "blogs_author_id");
        queryWrapper
                .eq("blogs_column", RoleID)
                .eq("blogs_status", BlogStatus.SUCCESS);
        return lanBlogsTempMapper.selectList(queryWrapper);
    }

    /**
     * 获取不通过文章信息
     *
     * @param RoleID
     * @return
     */
    @Override
    public List<LanBlogsTemp> getUnPassBlogsByRoleId(int RoleID) {
        QueryWrapper<LanBlogsTemp> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "blogs_title", "blogs_cover", "blogs_summary", "blogs_author_id");
        queryWrapper
                .eq("blogs_column", RoleID)
                .eq("blogs_status", BlogStatus.FAIL);
        return lanBlogsTempMapper.selectList(queryWrapper);
    }

    /**
     * 获取预览信息
     *
     * @param id
     * @return
     */
    @Override
    public LanBlogsTemp getAllViewMessageByID(int id) {
        QueryWrapper<LanBlogsTemp> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "blogs_author_id", "blogs_create_data", "blogs_title", "blogs_summary", "blogs_cover", "blogs_html", "blogs_column");
        queryWrapper.eq("id", id);
        return lanBlogsTempMapper.selectOne(queryWrapper);
    }

    /**
     * 设置博客为通过
     *
     * @param tempId
     * @return
     */
    @Override
    public LanBlogsTemp setPassBlogsByTempId(int tempId) {
        UpdateWrapper<LanBlogsTemp> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", tempId).set("blogs_status", BlogStatus.SUCCESS);
        int num = lanBlogsTempMapper.update(null, updateWrapper);
        if (num == 0) {
            return null;
        } else {
            QueryWrapper<LanBlogsTemp> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("blogs_author_id", "blogs_title", "blogs_summary", "blogs_cover", "blogs_html", "blogs_content", "blogs_column");
            queryWrapper.eq("id", tempId);
            return lanBlogsTempMapper.selectOne(queryWrapper);
        }
    }

    /**
     * 设置博客为不通过
     *
     * @param tempId
     * @return
     */
    @Override
    public int setUnPassBlogsBytempId(int tempId) {
        UpdateWrapper<LanBlogsTemp> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", tempId).set("blogs_status", BlogStatus.FAIL);
        return lanBlogsTempMapper.update(null, updateWrapper);
    }
}
