package com.huige.erp.common.constants;

/**
 * @Author Z.xichao
 * @Create 2018-9-28
 * @Comments
 */
public interface LogTypes {
    /**
     *
     * 操作状态（成功与否Y\\N）
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    static interface operateStatus
    {
        // 成功
        final String Y = "Y";

        // 失败
        final String N = "N";
    }

    /**
     *
     * 日志类型
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    static interface type
    {
        // 操作日志
        final String operate = "operate";

        // 异常日志
        final String exception = "exception";
    }

    /**
     *
     * 模块类型
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    static interface moduleType
    {
        // 客户中心（权限认证）
        final String AC = "AC";

        // 系统模块
        final String SYSTEM = "SYSTEM";

        // 通用模块
        final String COMMMON = "COMMMON";

        // 客户关系管理系统
        final String CRM = "CRM";

        //人力资源管理系统
        final String HRS = "HRS";

        // 移动端
        final String MOBILE = "MOBILE";

        // 自动化办公
        final String OA = "OA";
    }

    /**
     *
     * 操作类型
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    static interface operateValue
    {
        // 页面
        final String page = "page";

        // 查询
        final String select = "select";

        // 登录
        final String login = "login";

        // 保存
        final String save = "save";

        // 新增
        final String add = "add";

        // 修改
        final String update = "update";

        // 删除
        final String delete = "delete";

        // 查看
        final String view = "view";

        // 修改密码
        final String editPassword = "editPassword";

        // 上传
        final String upload = "upload";

        // 下载
        final String down = "down";

        // 下载
        final String packagedown = "packagedown";

        // 踢出
        final String kickedout = "kickedout";

    }

    /**
     *
     * 保存描述的前缀
     *          方便于批量修改该备注
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    static interface Prefix
    {
        // 查询
        final String savePrefix = "新增/编辑";

    }
}
