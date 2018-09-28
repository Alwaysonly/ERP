package com.huige.erp.ac.pojo.po;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 用户职位信息表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public class TAcPositionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 职位编码
     */
    private String code;
    /**
     * 职位名称
     */
    private String name;
    /**
     * 权限值(权限值大的默认有用比他小的所有权限)
     */
    private Integer weight;
    private Date createTime;
    private Date updateTime;
    private Integer order;
    /**
     * 可用状态
     */
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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
        return "TAcPositionInfo{" +
        ", id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", weight=" + weight +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", order=" + order +
        ", available=" + available +
        "}";
    }
}
