package com.huige.erp.ac.apis.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.huige.erp.ac.pojo.dto.BeanUserInfoList;
import com.huige.erp.ac.pojo.po.TAcUserInfo;
import com.huige.erp.common.base.IBaseService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public interface TAcUserInfoService extends IBaseService<TAcUserInfo> {
    TAcUserInfo getUserLogin(String userAccount,String password);

    Page<BeanUserInfoList> selectUserInfoList(Page<BeanUserInfoList> page, String userAccount);

    Boolean insertUserInfoWithUserRole(TAcUserInfo tAcUserInfo, Long userRole);
}
