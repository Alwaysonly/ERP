package com.huige.erp.ac.apis.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.huige.erp.ac.pojo.dto.BeanUserInfoList;
import com.huige.erp.ac.pojo.po.TAcUserInfo;
import com.huige.erp.common.base.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public interface TAcUserInfoMapper extends IBaseMapper<TAcUserInfo> {

    TAcUserInfo getUserLogin(@Param("userAccount") String userAccount, @Param("password") String password);

    int checkDepartmentUse(@Param("departmentId") Long departmentId);

    List<BeanUserInfoList> selectUserInfoList(Pagination page, @Param("userAccount") String userAccount);
}
