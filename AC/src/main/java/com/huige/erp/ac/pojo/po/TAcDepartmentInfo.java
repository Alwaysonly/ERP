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
 * 部门信息表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-09-14
 */
public class TAcDepartmentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "ID不能为空",groups = {AppConfigGroup.Update.class})
    private Long id;
    /**
     * 部门编码
     */
    @NotBlank(message = "部门编码不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    @Length(max = 64,message = "部门编码过长",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String code;
    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    @Length(max = 32,message = "部门名称过长",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String name;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 顺序号
     */
    @NotNull(message = "顺序号不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private Integer order;
    /**
     * 是否可用
     */
    @NotNull(message = "可用标志不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String available;


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
        return "TAcDepartmentInfo{" +
        ", id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", order=" + order +
        ", available=" + available +
        "}";
    }
}
