package com.huige.erp.ac.apis.service;

import com.huige.erp.ac.pojo.po.TAcRolePermission;
import com.huige.erp.common.base.IBaseService;

/**
 * <p>
 * 角色权限关系表 服务类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public interface TAcRolePermissionService extends IBaseService<TAcRolePermission> {

    void updatePermissionByRoleId(Integer[] authids, Long roleId);
}
