package com.huige.erp.ac.pojo.po;

import com.huige.erp.common.validator.AppConfigGroup;

import javax.validation.constraints.NotNull;
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

    @NotNull(message = "用户ID不能为空")
    private Long userId;
    @NotNull(message = "权限角色ID不能为空")
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
