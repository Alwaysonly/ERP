package com.huige.erp.ac.pojo.dto.tree;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author Z.xichao
 * @Create 2018-9-18
 * @Comments
 */
public class BeanTreePermission implements Serializable {

    private static final long serialVersionUID = 5227667447541692634L;

    /**
     * id
     */
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
    private Long position;
    /**
     * 父id
     */
    private Long pid;
    /**
     * 资源类型
     */
    private String type;
    /**
     * 图标
     */
    private String icon;
    /**
     * url
     */
    private String url;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 选中标志
     */
    private Boolean checked;

    /**
     * 子节点
     */
    private List<BeanTreePermission> children;

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

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<BeanTreePermission> getChildren() {
        return children;
    }

    public void setChildren(List<BeanTreePermission> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "BeanTreePermission{" +
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
                ", checked=" + checked +
                ", children=" + children +
                '}';
    }
}
