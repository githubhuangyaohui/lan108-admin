package com.persistenthuang.lan108admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.persistenthuang.lan108admin.mapper.LanAdminMapper;
import com.persistenthuang.lan108admin.pojo.LanAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanAdminServiceImpl implements LanAdminService {

    @Autowired
    LanAdminMapper lanAdminMapper;

    /**
     * 通过专栏ID判断专栏是否有管理员
     *
     * @param roleId
     * @return
     */
    @Override
    public boolean isExitByRoleId(int roleId) {
        QueryWrapper<LanAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        List<LanAdmin> lanAdmins = lanAdminMapper.selectList(queryWrapper);
        return lanAdmins != null;
    }

    /**
     * 通过UserID判断专栏是否有管理员
     *
     * @param userId
     * @return
     */
    @Override
    public boolean isExitByUserId(int userId) {
        QueryWrapper<LanAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<LanAdmin> lanAdmins = lanAdminMapper.selectList(queryWrapper);
        return lanAdmins.size() != 0;
    }

    /**
     * 通过专栏ID获取管理员userID
     *
     * @param roleId
     * @return
     */
    @Override
    public int getUserIdByRoleId(int roleId) {
        QueryWrapper<LanAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        LanAdmin lanAdmin = lanAdminMapper.selectOne(queryWrapper);
        return lanAdmin.getUserId();
    }

    /**
     * 通过userID获取专栏ID
     *
     * @param userID
     * @return
     */
    @Override
    public int getRoleIdByUserId(int userID) {
        QueryWrapper<LanAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userID);
        LanAdmin lanAdmin = lanAdminMapper.selectOne(queryWrapper);
        return lanAdmin.getRoleId();
    }
}
