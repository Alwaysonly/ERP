package com.huige.erp.ac.apis.service.impl;

import com.huige.erp.ac.apis.dao.TAcPermissionInfoMapper;
import com.huige.erp.ac.pojo.po.TAcPositionInfo;
import com.huige.erp.ac.apis.dao.TAcPositionInfoMapper;
import com.huige.erp.ac.apis.service.TAcPositionInfoService;
import com.huige.erp.common.base.BaseServiceImpl;
import com.huige.erp.common.exception.AppCommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 * 用户职位信息表 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
@Service
public class TAcPositionInfoServiceImpl extends BaseServiceImpl<TAcPositionInfoMapper, TAcPositionInfo> implements TAcPositionInfoService {

    @Autowired
    private TAcPermissionInfoMapper tAcPermissionInfoMapper;

    @Override
    public Set<String> findAllPositionCodeByUserId(Long userId) {
        return baseMapper.findAllPositionCodeByUserId(userId);
    }

    @Override
    public boolean safelyDelete(Long positionId) {
        if (tAcPermissionInfoMapper.checkPositionUse(positionId) > 0) {
            throw new AppCommonException("抱歉,职位信息已被占用,请解除后重试...");
        } else {
            return baseMapper.deleteById(positionId) > 0;
        }
    }
}
