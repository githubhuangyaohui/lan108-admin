package com.persistenthuang.lan108admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.persistenthuang.lan108admin.mapper.LanAuthorMapper;
import com.persistenthuang.lan108admin.pojo.LanAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanAuthorServiceImpl implements LanAuthorService {
    @Autowired
    LanAuthorMapper lanAuthorMapper;

    @Override
    public List<LanAuthor> ListAll() {
        return lanAuthorMapper.selectList(null);
    }

    @Override
    public LanAuthor getByAuthorID(int authorID) {
        return lanAuthorMapper.selectById(authorID);
    }

    @Override
    public LanAuthor getAllAuthorMessageById(int authorID) {
        QueryWrapper<LanAuthor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", authorID);
        queryWrapper.select("id", "author_show_name", "author_email", "author_create_data");
        LanAuthor lanAuthor = lanAuthorMapper.selectOne(queryWrapper);
        return lanAuthor;
    }

    @Override
    public int addAuthor(LanAuthor lanAuthor) {
        this.lanAuthorMapper.insert(lanAuthor);
        return lanAuthor.getId();
    }

    @Override
    public int updateAuthor(LanAuthor lanAuthor) {
        return this.lanAuthorMapper.updateById(lanAuthor);
    }

    @Override
    public boolean isExitByAuthorEmail(String email) {
        QueryWrapper<LanAuthor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_email", email);
        LanAuthor lanAuthor = lanAuthorMapper.selectOne(queryWrapper);
        return null != lanAuthor;
    }

    @Override
    public boolean isExitByAuthorPhone(String phone) {
        QueryWrapper<LanAuthor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_phone", phone);
        LanAuthor lanAuthor = lanAuthorMapper.selectOne(queryWrapper);
        return null != lanAuthor;
    }
}
