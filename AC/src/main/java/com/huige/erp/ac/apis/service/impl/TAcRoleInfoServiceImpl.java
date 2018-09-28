package com.huige.erp.ac.apis.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.huige.erp.ac.apis.dao.TAcRolePermissionMapper;
import com.huige.erp.ac.apis.dao.TAcUserRoleMapper;
import com.huige.erp.ac.pojo.dto.tree.BeanTreePermission;
import com.huige.erp.ac.pojo.po.TAcRoleInfo;
import com.huige.erp.ac.apis.dao.TAcRoleInfoMapper;
import com.huige.erp.ac.apis.service.TAcRoleInfoService;
import com.huige.erp.ac.pojo.po.TAcRolePermission;
import com.huige.erp.common.base.BaseServiceImpl;
import com.huige.erp.common.exception.AppCommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
@Service
public class TAcRoleInfoServiceImpl extends BaseServiceImpl<TAcRoleInfoMapper, TAcRoleInfo> implements TAcRoleInfoService {

    @Autowired
    private TAcUserRoleMapper tAcUserRoleMapper;

    @Autowired
    private TAcRolePermissionMapper tAcRolePermissionMapper;

    @Override
    public boolean safelyDelete(Long roleId) {
        if (tAcUserRoleMapper.checkRoleUse(roleId) > 0) {
            throw new AppCommonException("该角色已被占用,请解除后重试...");
        } else {
            tAcRolePermissionMapper.delete(new EntityWrapper<TAcRolePermission>().eq("t_ac_role_permission.role_id", roleId));
            return this.baseMapper.deleteById(roleId) > 0;
        }
    }

    @Override
    public List<BeanTreePermission> getPermissionTreeByRoleId(Long roleId) {
        List<BeanTreePermission> rootData = tAcRolePermissionMapper.getPermissionTreeByRoleId(roleId);
        return getPermissionTree(0L, rootData);
    }

    public List<BeanTreePermission> getPermissionTree(Long id, List<BeanTreePermission> rootData) {

        List<BeanTreePermission> childList = new ArrayList<>();
        for (BeanTreePermission obj : rootData) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (obj.getPid() == id) {
                childList.add(obj);
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (BeanTreePermission obj : childList) {
            obj.setChildren(getPermissionTree(obj.getId(), rootData));
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
