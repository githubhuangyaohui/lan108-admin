package com.persistenthuang.lan108admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.persistenthuang.lan108admin.mapper.LanFocusMapper;
import com.persistenthuang.lan108admin.pojo.LanFocus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanFocusServiceImpl implements LanFocusService {
    @Autowired
    LanFocusMapper lanFocusMapper;

    /**
     * 判断是否关注
     *
     * @param userID userID
     * @param roleID roleID
     * @return 是否关注
     */
    @Override
    public boolean isFocus(int userID, int roleID) {
        QueryWrapper<LanFocus> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userID);
        queryWrapper.eq("role_id", roleID);
        LanFocus lanFocus = lanFocusMapper.selectOne(queryWrapper);
        return lanFocus != null;
    }

    /**
     * 关注专栏
     *
     * @param userID
     * @param roleID
     * @return
     */
    @Override
    public int getFocus(int userID, int roleID) {
        LanFocus lanFocus = new LanFocus();
        lanFocus.setUserId(userID);
        lanFocus.setRoleId(roleID);
        return lanFocusMapper.insert(lanFocus);
    }

    /**
     * 取消关注专栏
     *
     * @param userID
     * @param roleID
     * @return
     */
    @Override
    public int getUnnFocus(int userID, int roleID) {
        QueryWrapper<LanFocus> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userID);
        queryWrapper.eq("role_id", roleID);
        return lanFocusMapper.delete(queryWrapper);
    }

    @Override
    public List<LanFocus> getFocusUserByRoleId(int roleId) {
        QueryWrapper<LanFocus> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_id");
        queryWrapper.eq("role_id", roleId);
        return lanFocusMapper.selectList(queryWrapper);
    }
}
