package com.huige.erp.ac.apis.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.huige.erp.ac.apis.dao.TAcUserRoleMapper;
import com.huige.erp.ac.pojo.dto.BeanUserInfoList;
import com.huige.erp.ac.pojo.po.TAcUserInfo;
import com.huige.erp.ac.apis.dao.TAcUserInfoMapper;
import com.huige.erp.ac.apis.service.TAcUserInfoService;
import com.huige.erp.ac.pojo.po.TAcUserRole;
import com.huige.erp.common.base.BaseServiceImpl;
import com.huige.erp.common.dto.ResponseResult;
import com.huige.erp.common.exception.AppParmMissException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
@Service
public class TAcUserInfoServiceImpl extends BaseServiceImpl<TAcUserInfoMapper, TAcUserInfo> implements TAcUserInfoService {
    @Autowired
    private TAcUserRoleMapper tAcUserRoleMapper;

    @Override
    public TAcUserInfo getUserLogin(String userAccount, String password) {
        return baseMapper.getUserLogin(userAccount, password);
    }

    @Override
    public Page<BeanUserInfoList> selectUserInfoList(Page<BeanUserInfoList> page, String userAccount) {
        return page.setRecords(baseMapper.selectUserInfoList(page, userAccount));
    }

    @Override
    public Boolean insertUserInfoWithUserRole(TAcUserInfo tAcUserInfo, Long userRole) {
        if (userRole == null) {
            throw new AppParmMissException("请选择角色权限...");
        }
        if (baseMapper.insert(tAcUserInfo) > 0) {
            TAcUserRole tAcUserRole = new TAcUserRole();
            tAcUserRole.setRoleId(userRole);
            tAcUserRole.setUserId(tAcUserInfo.getId());
            if (tAcUserRoleMapper.insert(tAcUserRole) > 0) {
                return true;
            }
        }
        return false;
    }
}
