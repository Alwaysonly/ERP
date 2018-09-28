package com.huige.erp.common.pojo.po;

import com.baomidou.mybatisplus.enums.IdType;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import org.apache.shiro.authc.Account;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr.Zhang
 * @since 2018-09-28
 */
public class TCommonLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 请求来源，PC：PC端，WAP：WAP端 默认来源为pc
     */
    private String reqSource;
    /**
     * 日志类型，‘operate’:操作日志，‘exception’:异常日志
     */
    private String type;
    /**
     * 操作电脑ip
     */
    private String ip;
    /**
     * 操作人员名字
     */
    private String userName;
    /**
     * 操作人员登录账号
     */
    private String userAccount;
    /**
     * 模块代码
     */
    private String moduleType;
    /**
     * 操作代码
     */
    private String operateCode;
    /**
     * 操作类型
     */
    private String operateValue;
    /**
     * 操作时间
     */
    private Date operateDate;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 操作备注(记录参数)
     */
    private String remark;
    /**
     * 操作状态（成功与否Y\N）
     */
    private String operateStatus;
    /**
     * 服务器IP
     */
    private String localAddr;
    /**
     * 调用方法
     */
    private String method;
    /**
     * 方法的请求参数
     */
    private String param;
    /**
     * 异常信息
     */
    private String exceptionDetail;

    public void init()
    {
        operateDate = new Date();
        createDate = new Date();
        try {
            localAddr = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public TCommonLog() {
        init();
    }

    public TCommonLog(TCommonLog origin)
    {
        init();
        this.id = origin.id;
        this.type = origin.type;
        this.moduleType = origin.moduleType;
        this.operateCode = origin.operateCode;
        this.operateValue = origin.operateValue;
        this.remark = origin.remark;
        this.operateStatus = origin.operateStatus;
        this.method = origin.method;
        this.param = origin.param;
        this.exceptionDetail = origin.exceptionDetail;
        this.userName = origin.userName;
        this.userAccount = origin.userAccount;
        this.ip = origin.ip;
        this.reqSource = origin.reqSource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReqSource() {
        return reqSource;
    }

    public void setReqSource(String reqSource) {
        this.reqSource = reqSource;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }

    public String getOperateValue() {
        return operateValue;
    }

    public void setOperateValue(String operateValue) {
        this.operateValue = operateValue;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(String operateStatus) {
        this.operateStatus = operateStatus;
    }

    public String getLocalAddr() {
        return localAddr;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public static class Builder
    {

        private TCommonLog target;

        public Builder()
        {
            target = new TCommonLog();
        }

        public Builder id(Long id)
        {
            target.id = id;
            return this;
        }

        public Builder type(String type)
        {
            target.type = type;
            return this;
        }

        public Builder moduleType(String moduleType)
        {
            target.moduleType = moduleType;
            return this;
        }

        public Builder ip(String ip)
        {
            target.ip = ip;
            return this;
        }

        public Builder reqSource(String reqSource)
        {
            target.reqSource = reqSource;
            return this;
        }

        public Builder operateCode(String operateCode)
        {
            target.operateCode = operateCode;
            return this;
        }

        public Builder operateValue(String operateValue)
        {
            target.operateValue = operateValue;
            return this;
        }

        public Builder remark(String remark)
        {
            target.remark = remark;
            return this;
        }

        public Builder operateStatus(String operateStatus)
        {
            target.operateStatus = operateStatus;
            return this;
        }

        public Builder method(String method)
        {
            target.method = method;
            return this;
        }

        public Builder param(String param)
        {
            target.param = param;
            return this;
        }

        public Builder exceptionDetail(String exceptionDetail)
        {
            target.exceptionDetail = exceptionDetail;
            return this;
        }

        public Builder userAccount(String userAccount)
        {
            target.userAccount = userAccount;
            return this;
        }

        public Builder userName(String userName)
        {
            target.userName = userName;
            return this;
        }

        public TCommonLog build()
        {
            return new TCommonLog(target);
        }

    }

    @Override
    public String toString() {
        return "TCommonLog{" +
        ", id=" + id +
        ", reqSource=" + reqSource +
        ", type=" + type +
        ", ip=" + ip +
        ", userName=" + userName +
        ", userAccount=" + userAccount +
        ", moduleType=" + moduleType +
        ", operateCode=" + operateCode +
        ", operateValue=" + operateValue +
        ", operateDate=" + operateDate +
        ", createDate=" + createDate +
        ", remark=" + remark +
        ", operateStatus=" + operateStatus +
        ", localAddr=" + localAddr +
        ", method=" + method +
        ", param=" + param +
        ", exceptionDetail=" + exceptionDetail +
        "}";
    }
}
