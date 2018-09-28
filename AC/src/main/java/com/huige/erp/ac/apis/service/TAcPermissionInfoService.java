package com.huige.erp.ac.apis.service;

import com.huige.erp.ac.pojo.dto.BeanPermissionInjection;
import com.huige.erp.ac.pojo.dto.tree.BeanTreePermission;
import com.huige.erp.ac.pojo.po.TAcPermissionInfo;
import com.huige.erp.common.base.IBaseService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限信息表 服务类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public interface TAcPermissionInfoService extends IBaseService<TAcPermissionInfo> {

    Set<String> findAllPermissionsByUserId(Long userId);

    List<TAcPermissionInfo> getTopLevelMenus();

    List<BeanPermissionInjection> getPermissionInjections();

    boolean safelyDelete(Long id);

    List<BeanTreePermission> getNavsTreeData();
}
