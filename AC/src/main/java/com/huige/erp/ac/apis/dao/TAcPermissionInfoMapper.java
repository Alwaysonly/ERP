package com.huige.erp.ac.apis.dao;

import com.huige.erp.ac.pojo.dto.BeanPermissionInjection;
import com.huige.erp.ac.pojo.dto.tree.BeanTreePermission;
import com.huige.erp.ac.pojo.po.TAcPermissionInfo;
import com.huige.erp.common.base.IBaseMapper;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限信息表 Mapper 接口
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public interface TAcPermissionInfoMapper extends IBaseMapper<TAcPermissionInfo> {

    Set<String> findAllPermissionsByUserId(Long userId);

    List<BeanPermissionInjection> getPermissionInjections();

    Integer checkPositionUse(Long positionId);

    List<BeanTreePermission> getPermissionByUserId(Long userId);
}
