package com.persistenthuang.lan108admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.persistenthuang.lan108admin.config.BlogStatus;
import com.persistenthuang.lan108admin.mapper.LanBlogsMapper;
import com.persistenthuang.lan108admin.pojo.LanBlogs;
import com.persistenthuang.lan108admin.pojo.LanBlogsTemp;
import com.persistenthuang.lan108admin.pojo.LanMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanBlogsServiceImpl implements LanBlogsService {
    @Autowired
    LanBlogsMapper lanBlogsMapper;

    /**
     * 通过blog的ID查询全部数据
     *
     * @param id
     * @return 博客信息:LanBlogs
     */
    @Override
    public LanBlogs getBlogAllMessageByID(int id) {
        QueryWrapper<LanBlogs> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.select("id", "blogs_author_id", "blogs_create_data", "blogs_title", "blogs_summary", "blogs_cover", "blogs_html", "blogs_column");
        return lanBlogsMapper.selectOne(queryWrapper);
    }


    /**
     * 首页推荐分页查询
     *
     * @param page
     * @return 分页查询列表:List<LanBlogs>
     */
    @Override
    public List<LanBlogs> selectBlogPage(Page<LanBlogs> page) {
        QueryWrapper<LanBlogs> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "blogs_title", "blogs_column", "blogs_author_id", "blogs_cover", "blogs_summary");
        queryWrapper.eq("blogs_status", BlogStatus.SIMPLE);
        queryWrapper.orderByDesc("blogs_create_data");
        IPage<LanBlogs> iPage = lanBlogsMapper.selectPage(page, queryWrapper);

        return iPage.getRecords();
    }

    /**
     * 返回置顶文章列表
     *
     * @return
     */
    @Override
    public List<LanBlogs> selectBlogIndexFirst() {
        QueryWrapper<LanBlogs> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "blogs_title", "blogs_column", "blogs_author_id", "blogs_cover", "blogs_summary");
        queryWrapper.eq("blogs_status", BlogStatus.FIRST);
        return lanBlogsMapper.selectList(queryWrapper);
    }

    /**
     * 关键值查询
     *
     * @param keywords
     * @return 查询列表:List<LanBlogs>
     */
    @Override
    public List<LanBlogs> SearchListByKeywords(String keywords) {
        QueryWrapper<LanBlogs> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("blogs_title", keywords);
        queryWrapper.select("id", "blogs_title", "blogs_summary", "blogs_cover", "blogs_author_id");
        return lanBlogsMapper.selectList(queryWrapper);
    }

    /**
     * 获取专栏文章列表
     *
     * @param roleId
     * @return
     */
    @Override
    public List<LanBlogs> getBlogsByRoleID(int roleId) {
        QueryWrapper<LanBlogs> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blogs_column", roleId);
        queryWrapper.select("id", "blogs_title", "blogs_cover", "blogs_summary", "blogs_author_id");
        return lanBlogsMapper.selectList(queryWrapper);
    }

    /**
     * 通过作者ID获取博客
     *
     * @param authorID
     * @return
     */
    @Override
    public List<LanBlogs> getBlogsByAuthorID(int authorID) {
        QueryWrapper<LanBlogs> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blogs_author_id", authorID);
        queryWrapper.select("id", "blogs_title", "blogs_cover", "blogs_summary", "blogs_author_id");
        return lanBlogsMapper.selectList(queryWrapper);
    }

    /**
     * 根据博客id集合获取博客列表，基本信息
     *
     * @param massage
     * @return
     */
    @Override
    public List<LanBlogs> getFocusBlogsByMsg(List<LanMassage> massage) {
        try {
            List<Integer> list = massage.stream().map(LanMassage::getBlogId).collect(Collectors.toList());
            QueryWrapper<LanBlogs> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("id", "blogs_title", "blogs_cover", "blogs_summary", "blogs_author_id");
            queryWrapper.in("id", list);
            return lanBlogsMapper.selectList(queryWrapper);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 添加博客
     *
     * @param lanBlogsTemp 通过临时文件
     * @return
     */
    @Override
    public int insertLanBlogsByTemp(LanBlogsTemp lanBlogsTemp) {
        LanBlogs lanBlogs = new LanBlogs();
        lanBlogs.setBlogsAuthorId(lanBlogsTemp.getBlogsAuthorId());
        lanBlogs.setBlogsTitle(lanBlogsTemp.getBlogsTitle());
        lanBlogs.setBlogsSummary(lanBlogsTemp.getBlogsSummary());
        lanBlogs.setBlogsCover(lanBlogsTemp.getBlogsCover());
        lanBlogs.setBlogsHtml(lanBlogsTemp.getBlogsHtml());
        lanBlogs.setBlogsContent(lanBlogsTemp.getBlogsContent());
        lanBlogs.setBlogsColumn(lanBlogsTemp.getBlogsColumn());
        lanBlogsMapper.insert(lanBlogs);
        return lanBlogs.getId();
    }

    /**
     * 获取博客所属专栏Id
     *
     * @param BlogId
     * @return
     */
    @Override
    public int getBlogRoleIdByBlogId(int BlogId) {
        QueryWrapper<LanBlogs> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("blogs_column");
        queryWrapper.eq("id", BlogId);
        LanBlogs lanBlogs = lanBlogsMapper.selectOne(queryWrapper);
        return lanBlogs.getBlogsColumn();
    }
}
