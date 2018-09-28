package com.huige.erp.ac.apis.service.impl;

import com.huige.erp.ac.pojo.po.TAcRolePermission;
import com.huige.erp.ac.apis.dao.TAcRolePermissionMapper;
import com.huige.erp.ac.apis.service.TAcRolePermissionService;
import com.huige.erp.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色权限关系表 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
@Service
public class TAcRolePermissionServiceImpl extends BaseServiceImpl<TAcRolePermissionMapper, TAcRolePermission> implements TAcRolePermissionService {

    @Override
    public void updatePermissionByRoleId(Integer[] authids, Long roleId) {
        baseMapper.deleteByRoleId(roleId);
        if (authids.length > 0) {
            List<TAcRolePermission> rolePermissions = new ArrayList<>();
            for (int i = 0; i < authids.length; i++) {
                TAcRolePermission obj = new TAcRolePermission();
                obj.setPermissionId(Long.valueOf(authids[i]));
                obj.setRoleId(roleId);
                rolePermissions.add(obj);
            }
            baseMapper.insertByBatch(rolePermissions);
        }
    }
}
