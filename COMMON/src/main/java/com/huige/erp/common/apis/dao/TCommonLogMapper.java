package com.huige.erp.common.apis.dao;

import com.huige.erp.common.pojo.po.TCommonLog;
import com.huige.erp.common.base.IBaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-09-28
 */
public interface TCommonLogMapper extends IBaseMapper<TCommonLog> {

    void execTruncation();
}
