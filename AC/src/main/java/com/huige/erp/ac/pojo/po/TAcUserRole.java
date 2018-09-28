package com.huige.erp.ac.pojo.po;

import java.io.Serializable;

/**
 * <p>
 * 用户权限关系表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public class TAcUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long roleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "TAcUserRole{" +
        ", userId=" + userId +
        ", roleId=" + roleId +
        "}";
    }
}
