package com.huige.erp.ac.apis.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.huige.erp.ac.apis.dao.TAcRolePermissionMapper;
import com.huige.erp.ac.apis.service.TAcRoleInfoService;
import com.huige.erp.ac.apis.service.TAcRolePermissionService;
import com.huige.erp.ac.configuration.AcConfiguration;
import com.huige.erp.ac.pojo.dto.BeanPermissionInjection;
import com.huige.erp.ac.pojo.dto.tree.BeanTreePermission;
import com.huige.erp.ac.pojo.po.TAcPermissionInfo;
import com.huige.erp.ac.apis.dao.TAcPermissionInfoMapper;
import com.huige.erp.ac.apis.service.TAcPermissionInfoService;
import com.huige.erp.ac.pojo.po.TAcUserInfo;
import com.huige.erp.common.base.BaseServiceImpl;
import com.huige.erp.common.exception.AppCommonException;
import com.huige.erp.common.exception.AppRedirectException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限信息表 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
@Service
public class TAcPermissionInfoServiceImpl extends BaseServiceImpl<TAcPermissionInfoMapper, TAcPermissionInfo> implements TAcPermissionInfoService {

    @Autowired
    private TAcRolePermissionMapper tAcRolePermissionMapper;

    @Autowired
    private TAcRoleInfoService tAcRoleInfoService;

    @Autowired
    private AcConfiguration acConfiguration;

    @Override
    public Set<String> findAllPermissionsByUserId(Long userId) {
        return baseMapper.findAllPermissionsByUserId(userId);
    }

    @Override
    public List<TAcPermissionInfo> getTopLevelMenus() {
        return baseMapper.selectList(new EntityWrapper<TAcPermissionInfo>().eq("pid", "0"));
    }

    @Override
    public List<BeanPermissionInjection> getPermissionInjections() {
        return baseMapper.getPermissionInjections();
    }

    @Override
    public boolean safelyDelete(Long permissionId) {
        if (tAcRolePermissionMapper.checkPermissionUse(permissionId) > 0) {
            throw new AppCommonException("该权限被其他角色使用，请解除后重试...");
        } else {
            return baseMapper.deleteById(permissionId) > 0;
        }
    }

    @Override
    public List<BeanTreePermission> getNavsTreeData() {
        TAcUserInfo userInfo = (TAcUserInfo) SecurityUtils.getSubject().getPrincipal();
        if (userInfo == null || userInfo.getId() == null) {
            throw new AppRedirectException("获取用户信息失败，请重新登录。", acConfiguration.getLoginEndpoint());
        }
        List<BeanTreePermission> rootData = baseMapper.getPermissionByUserId(userInfo.getId());
        return tAcRoleInfoService.getPermissionTree(0L, rootData);
    }

}
