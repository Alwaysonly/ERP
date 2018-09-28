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
 * 权限信息表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public class TAcPermissionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "ID不能为空",groups = {AppConfigGroup.Update.class})
    private Long id;
    /**
     * 权限代码
     */
    @NotBlank(message = "权限编码不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    @Length(max = 64,message = "权限编代码过长",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String code;
    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    @Length(max = 64,message = "权限名称代码过长",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String name;
    /**
     * 职位权限
     */
    @NotNull(message = "职位权限不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private Long position;

    /**
     * 父id
     */
    @NotNull(message = "所属菜单不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private Long pid;
    /**
     * 资源类型
     */
    @NotNull(message = "资源类型不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String type;
    /**
     * 图标
     */
    @NotNull(message = "资源图标不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String icon;
    @NotNull(message = "资源路径不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String url;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    @NotNull(message = "序号不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String order;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TAcPermissionInfo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", pid=" + pid +
                ", type='" + type + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", order='" + order + '\'' +
                '}';
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

}
