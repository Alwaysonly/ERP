package com.huige.erp.ac.pojo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Z.xichao
 * @Create 2018-9-19
 * @Comments
 */
public class BeanUserInfoList implements Serializable {

    private static final long serialVersionUID = -5745434526114813230L;

    private Long id;
    private String userAccount;
    private String userName;
    private String userSex;
    private Date userBirthday;
    private String userPhone;
    private Long userDepartment;
    private String userDepartmentDesc;
    private Long userPosition;
    private String userPositionDesc;
    private Long userRole;
    private String userRoleName;
    private Short locked;
    private Short deleted;
    private String version;
    private Date createTime;
    private Date updateTime;
    private Integer order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Long getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(Long userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getUserDepartmentDesc() {
        return userDepartmentDesc;
    }

    public void setUserDepartmentDesc(String userDepartmentDesc) {
        this.userDepartmentDesc = userDepartmentDesc;
    }

    public Long getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(Long userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserPositionDesc() {
        return userPositionDesc;
    }

    public void setUserPositionDesc(String userPositionDesc) {
        this.userPositionDesc = userPositionDesc;
    }

    public Long getUserRole() {
        return userRole;
    }

    public void setUserRole(Long userRole) {
        this.userRole = userRole;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public Short getLocked() {
        return locked;
    }

    public void setLocked(Short locked) {
        this.locked = locked;
    }

    public Short getDeleted() {
        return deleted;
    }

    public void setDeleted(Short deleted) {
        this.deleted = deleted;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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


    @Override
    public String toString() {
        return "BeanUserInfoList{" +
                "id=" + id +
                ", userAccount='" + userAccount + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBirthday=" + userBirthday +
                ", userPhone='" + userPhone + '\'' +
                ", userDepartment=" + userDepartment +
                ", userDepartmentDesc='" + userDepartmentDesc + '\'' +
                ", userPosition=" + userPosition +
                ", userPositionDesc='" + userPositionDesc + '\'' +
                ", userRole=" + userRole +
                ", userRoleName='" + userRoleName + '\'' +
                ", locked=" + locked +
                ", deleted=" + deleted +
                ", version='" + version + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", order=" + order +
                '}';
    }
}
