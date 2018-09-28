package com.huige.erp.ac.apis.service.impl;

import com.huige.erp.ac.apis.dao.TAcUserInfoMapper;
import com.huige.erp.ac.pojo.po.TAcDepartmentInfo;
import com.huige.erp.ac.apis.dao.TAcDepartmentInfoMapper;
import com.huige.erp.ac.apis.service.TAcDepartmentInfoService;
import com.huige.erp.common.base.BaseServiceImpl;
import com.huige.erp.common.exception.AppCommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门信息表 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-09-14
 */
@Service
public class TAcDepartmentInfoServiceImpl extends BaseServiceImpl<TAcDepartmentInfoMapper, TAcDepartmentInfo> implements TAcDepartmentInfoService {

    @Autowired
    private TAcUserInfoMapper tAcUserInfoMapper;

    @Override
    public boolean safelyDelete(Long departmentId) {
        if (tAcUserInfoMapper.checkDepartmentUse(departmentId) > 0) {
            throw new AppCommonException("当前部门已被使用，请解除后重试...");
        } else {
            return baseMapper.deleteById(departmentId) > 0;
        }
    }
}
