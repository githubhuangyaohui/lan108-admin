package com.persistenthuang.lan108admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.persistenthuang.lan108admin.mapper.LanMassageMapper;
import com.persistenthuang.lan108admin.pojo.LanMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanMassageServiceImpl implements LanMassageService {
    @Autowired
    LanMassageMapper lanMassageMapper;

    /**
     * 发送信息
     *
     * @param userID
     * @param BlogID
     * @return
     */
    @Override
    public boolean sendMessage(int userID, int BlogID) {
        try {
            LanMassage lanMassage = new LanMassage();
            lanMassage.setBlogId(BlogID);
            lanMassage.setUserId(userID);
            lanMassageMapper.insert(lanMassage);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    /**
     * 获取信息列表数组
     *
     * @param userID
     * @return
     */
    @Override
    public List<LanMassage> getMassageListByUserId(int userID) {
        QueryWrapper<LanMassage> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("blog_id");
        queryWrapper.eq("user_id", userID);
        queryWrapper.eq("msg_id_read", 0);
        return lanMassageMapper.selectList(queryWrapper);
    }

    /**
     * 设置已阅
     *
     * @param userID
     * @param blogID
     * @return
     */
    @Override
    public int setIsRead(int userID, int blogID) {
        UpdateWrapper<LanMassage> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .eq("user_id", userID)
                .eq("blog_id", blogID)
                .set("msg_id_read", 1);
        return lanMassageMapper.update(null, updateWrapper);
    }
}
