package com.huige.erp.system.apis.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.huige.erp.common.aop.AppControllerLog;
import com.huige.erp.common.apis.service.TCommonLogService;
import com.huige.erp.common.constants.Constants;
import com.huige.erp.common.constants.LogTypes;
import com.huige.erp.common.dto.BaseResponseResult;
import com.huige.erp.common.dto.ResponseResult;
import com.huige.erp.common.dto.ResponseResultWithListAll;
import com.huige.erp.common.pojo.po.TCommonLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @Author Z.xichao
 * @Create 2018-9-25
 * @Comments
 */
@RestController
@RequestMapping("/page/system")
@Api(tags = "1.系统管理")
public class SystemController {

    private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private TCommonLogService tCommonLogService;

    @Autowired
    private SessionDAO sessionDAO;

    /**
     * 在线用户管理
     *
     * @return
     */
    @GetMapping("/session")
    @AppControllerLog(description = "在线用户管理", moduleType = LogTypes.moduleType.SYSTEM, operateValue = LogTypes.operateValue.view)
    @ApiOperation("在线用户管理")
    public ModelAndView sessionView(ServletRequest request) {
        return new ModelAndView("page/system/session");
    }


    /**
     * 在线用户列表
     *
     * @return
     */
    @GetMapping("/sessions/list")
    @AppControllerLog(description = "在线用户列表", moduleType = LogTypes.moduleType.SYSTEM, operateValue = LogTypes.operateValue.select)
    @ApiOperation("在线用户列表")
    public ResponseResult sessionList(ServletRequest request) {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return new ResponseResult<>(sessions);
    }

    @GetMapping("/sessions/kickedout/{sessionId}")
    @AppControllerLog(description = "强制踢出用户", moduleType = LogTypes.moduleType.SYSTEM, operateValue = LogTypes.operateValue.kickedout)
    @ApiOperation("强制踢出用户")
    public ResponseResult sessionKickedOut(ServletRequest request, @PathVariable("sessionId") String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        if (session != null) {
            session.setAttribute(Constants.SESSION_FORCE_LOGOUT_KEY, Boolean.TRUE);
            sessionDAO.update(session);
            return new ResponseResult();
        } else {
            return new ResponseResult(BaseResponseResult.FAILURE, "获取session信息失败");
        }
    }

    /**
     * 日志管理
     *
     * @param request
     * @return
     */
    @GetMapping("/log")
    @AppControllerLog(description = "日志管理", moduleType = LogTypes.moduleType.SYSTEM, operateValue = LogTypes.operateValue.view)
    @ApiOperation("日志管理")
    public ModelAndView logView(ServletRequest request) {
        return new ModelAndView("page/system/log");
    }

    /**
     * 日志管理
     *
     * @param request
     * @return
     */
    @GetMapping("/logs/list")
    @AppControllerLog(description = "日志列表", moduleType = LogTypes.moduleType.SYSTEM, operateValue = LogTypes.operateValue.select)
    @ApiOperation("日志列表")
    public ResponseResultWithListAll logsList(ServletRequest request, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        Page<TCommonLog> commonLogList = tCommonLogService.selectPage(new Page<>(page, limit));
        return new ResponseResultWithListAll<>(commonLogList.getTotal(), commonLogList.getRecords());
    }

    /**
     * 清空日志
     *
     * @param request
     * @return
     */
    @GetMapping("/logs/truncation")
    @AppControllerLog(description = "清空日志", moduleType = LogTypes.moduleType.SYSTEM, operateValue = LogTypes.operateValue.delete)
    @ApiOperation("清空日志")
    public ResponseResult logsTruncation(ServletRequest request) {
        tCommonLogService.execTruncation();
        return new ResponseResult();
    }
}
