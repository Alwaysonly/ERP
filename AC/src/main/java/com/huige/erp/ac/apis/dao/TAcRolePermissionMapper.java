package com.huige.erp.ac.apis.dao;

import com.huige.erp.ac.pojo.dto.tree.BeanTreePermission;
import com.huige.erp.ac.pojo.po.TAcRolePermission;
import com.huige.erp.common.base.IBaseMapper;

import java.util.List;

/**
 * <p>
 * 角色权限关系表 Mapper 接口
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public interface TAcRolePermissionMapper extends IBaseMapper<TAcRolePermission> {

    Long checkPermissionUse(Long permissionId);

    List<BeanTreePermission> getPermissionTreeByRoleId(Long roleId);

    Integer deleteByRoleId(Long roleId);

    Integer insertByBatch(List<TAcRolePermission> rolePermissions);

}
