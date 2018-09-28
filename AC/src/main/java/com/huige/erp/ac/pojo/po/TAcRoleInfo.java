package com.huige.erp.ac.pojo.po;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.huige.erp.common.validator.AppConfigGroup;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public class TAcRoleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "ID不能为空",groups = {AppConfigGroup.Update.class})
    private Long id;
    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    @Length(max = 64,message = "角色编码过长",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String roleCode;
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    @Length(max = 64,message = "角色名称过长",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String roleName;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;
    @NotNull(message = "顺序号不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private Integer order;
    @NotBlank(message = "可用标志不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String available;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "TAcRoleInfo{" +
                "id=" + id +
                ", roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", order=" + order +
                ", available='" + available + '\'' +
                '}';
    }
}
