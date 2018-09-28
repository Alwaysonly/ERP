package com.huige.erp.ac.apis.dao;

import com.huige.erp.ac.pojo.po.TAcUserRole;
import com.huige.erp.common.base.IBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户权限关系表 Mapper 接口
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public interface TAcUserRoleMapper extends IBaseMapper<TAcUserRole> {

    int checkRoleUse(@Param("roleId") Long roleId);
}
