package com.huige.erp.ac.apis.service;

import com.huige.erp.ac.pojo.po.TAcDepartmentInfo;
import com.huige.erp.common.base.IBaseService;

/**
 * <p>
 * 部门信息表 服务类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-09-14
 */
public interface TAcDepartmentInfoService extends IBaseService<TAcDepartmentInfo> {

    boolean safelyDelete(Long departmentId);
}
