package com.huige.erp.ac.pojo.po;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.huige.erp.common.validator.AppConfigGroup;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-07-10
 */
public class TAcUserInfo implements Serializable {

    private static final long serialVersionUID = -2246162034925833369L;
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "ID不能为空",groups = {AppConfigGroup.Update.class})
    private Long id;
    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    @Length(max = 64,message = "账号过长",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String userAccount;
    /**
     * 用户名称
     */
    @NotBlank(message = "用户名称不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    @Length(max = 32,message = "用户名称过长",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String userName;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空",groups = {AppConfigGroup.Add.class})
    @Length(max = 64,message = "密码过长",groups = {AppConfigGroup.Add.class})
    private String userPassword;
    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String userSex;
    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userBirthday;
    /**
     * 联系方式
     */
    private String userPhone;
    /**
     * 所属部门
     */
    @NotNull(message = "所属部门不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private Long userDepartment;

    /**
     * 用户职位
     */
    @NotNull(message = "用户职位不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private Long userPosition;
    /**
     * 冻结
     */
    private Boolean locked;
    /**
     * 失效
     */
    private Boolean deleted;
    /**
     * 权限版本
     */
    @NotNull(message = "权限版本不能为空",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    @Length(max = 36,message = "权限编码过长",groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})
    private String version;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public Long getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(Long userPosition) {
        this.userPosition = userPosition;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
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

    @Override
    public String toString() {
        return "TAcUserInfo{" +
                "id=" + id +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBirthday=" + userBirthday +
                ", userPhone='" + userPhone + '\'' +
                ", userDepartment=" + userDepartment +
                ", userPosition=" + userPosition +
                ", locked=" + locked +
                ", deleted=" + deleted +
                ", version='" + version + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
