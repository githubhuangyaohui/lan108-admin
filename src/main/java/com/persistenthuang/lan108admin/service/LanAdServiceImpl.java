package com.persistenthuang.lan108admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.persistenthuang.lan108admin.mapper.LanAdMapper;
import com.persistenthuang.lan108admin.pojo.LanAd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanAdServiceImpl implements LanAdService {

    @Autowired
    LanAdMapper lanAdMapper;

    @Override
    public List<LanAd> getLanAdList() {
        QueryWrapper<LanAd> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "lan_ad_image", "lan_ad_link");
        return lanAdMapper.selectList(queryWrapper);
    }
}
