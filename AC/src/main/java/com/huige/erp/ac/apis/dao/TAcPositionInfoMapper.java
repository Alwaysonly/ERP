package com.huige.erp.ac.apis.dao;

import com.huige.erp.ac.pojo.po.TAcPositionInfo;
import com.huige.erp.common.base.IBaseMapper;

import java.util.Set;

/**
 * <p>
 * 用户职位信息表 Mapper 接口
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public interface TAcPositionInfoMapper extends IBaseMapper<TAcPositionInfo> {

    Set<String> findAllPositionCodeByUserId(Long userId);
}
