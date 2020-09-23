package com.persistenthuang.lan108admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.persistenthuang.lan108admin.mapper.LanRolesMapper;
import com.persistenthuang.lan108admin.pojo.LanRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanRolesServiceImpl implements LanRolesService {
    @Autowired
    LanRolesMapper lanRolesMapper;

    /**
     * 获取专栏信息通过ID'
     *
     * @param RolesId
     * @return
     */
    @Override
    public LanRoles getRolesMessageByID(int RolesId) {
        QueryWrapper<LanRoles> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "roles_lan_name", "roles_starname", "roles_nickname", "roles_truename", "roles_description", "roles_img");
        queryWrapper.eq("id", RolesId);
        queryWrapper.eq("roles_state", 1);
        return lanRolesMapper.selectOne(queryWrapper);
    }

    /**
     * 获取文章查看页专栏信息
     *
     * @param RolesId
     * @return
     */
    @Override
    public LanRoles getViewRolesMessageByID(int RolesId) {
        QueryWrapper<LanRoles> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "roles_lan_name");
        queryWrapper.eq("id", RolesId);
        queryWrapper.eq("roles_state", 1);
        return lanRolesMapper.selectOne(queryWrapper);
    }

    /**
     * 获取所有专栏
     *
     * @return
     */
    @Override
    public List<LanRoles> getAllList() {
        QueryWrapper<LanRoles> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "roles_lan_name", "roles_nickname", "roles_truename", "roles_img");
        queryWrapper.eq("roles_state", 1);
        return lanRolesMapper.selectList(queryWrapper);
    }

    /**
     * 获取所有专栏名称
     *
     * @return
     */
    @Override
    public List<LanRoles> getAllRolesName() {
        QueryWrapper<LanRoles> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "roles_lan_name");
        queryWrapper.eq("roles_state", 1);
        return lanRolesMapper.selectList(queryWrapper);
    }

    @Override
    public int UpdateLanRoleByRoleId(LanRoles role) {
        UpdateWrapper<LanRoles> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", role.getId())
                .set("roles_lan_name", role.getRolesLanName())
                .set("roles_description", role.getRolesDescription())
                .set("roles_img", role.getRolesImg());
        return lanRolesMapper.update(null, updateWrapper);
    }
}
