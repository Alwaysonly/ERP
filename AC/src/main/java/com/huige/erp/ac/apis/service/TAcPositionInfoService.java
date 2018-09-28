package com.huige.erp.ac.apis.service;

import com.huige.erp.ac.pojo.po.TAcPositionInfo;
import com.huige.erp.common.base.IBaseService;

import java.util.Set;

/**
 * <p>
 * 用户职位信息表 服务类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public interface TAcPositionInfoService extends IBaseService<TAcPositionInfo> {

    Set<String> findAllPositionCodeByUserId(Long userId);

    boolean safelyDelete(Long positionId);
}
