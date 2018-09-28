package com.huige.erp.ac.apis.service;

import com.huige.erp.ac.pojo.dto.tree.BeanTreePermission;
import com.huige.erp.ac.pojo.po.TAcRoleInfo;
import com.huige.erp.common.base.IBaseService;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public interface TAcRoleInfoService extends IBaseService<TAcRoleInfo> {

    boolean safelyDelete(Long roleId);

    List<BeanTreePermission> getPermissionTreeByRoleId(Long roleId);

    public List<BeanTreePermission> getPermissionTree(Long id, List<BeanTreePermission> rootData) ;
}
