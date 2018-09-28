package com.huige.erp.ac.pojo.dto;

import java.io.Serializable;

/**
 * @Author Z.xichao
 * @Create 2018-9-5
 * @Comments
 */
public class BeanPermissionInjection implements Serializable {

    private static final long serialVersionUID = 266082250247248429L;

    private Long id;
    /**
     * 权限代码
     */
    private String code;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 职位权限
     */
    private String positionCode;

    private String url;

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

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
