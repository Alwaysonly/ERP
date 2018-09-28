package com.huige.erp.ac.pojo.po;

import com.huige.erp.common.validator.AppConfigGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 角色权限关系表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public class TAcRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "权限角色ID不能为空")
    private Long roleId;
    @NotNull(message = "权限ID不能为空")
    private Long permissionId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "TAcRolePermission{" +
        ", roleId=" + roleId +
        ", permissionId=" + permissionId +
        "}";
    }
}
