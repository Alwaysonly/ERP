package com.huige.erp.system.apis.controller;

import com.huige.erp.common.aop.AppControllerLog;
import com.huige.erp.common.constants.Constants;
import com.huige.erp.common.constants.LogTypes;
import com.huige.erp.common.dto.BaseResponseResult;
import com.huige.erp.common.dto.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.util.Collection;

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
    private SessionDAO sessionDAO;

    /**
     * 在线用户管理
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
     * @return
     */
    @GetMapping("/sessions/list")
    @AppControllerLog(description = "在线用户列表", moduleType = LogTypes.moduleType.SYSTEM, operateValue = LogTypes.operateValue.select)
    @ApiOperation("在线用户列表")
    public ResponseResult sessionList() {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return new ResponseResult<>(sessions);
    }

    @GetMapping("/sessions/kickedout/{sessionId}")
    @AppControllerLog(description = "强制踢出用户", moduleType = LogTypes.moduleType.SYSTEM, operateValue = LogTypes.operateValue.kickedout)
    @ApiOperation("强制踢出用户")
    public ResponseResult sessionKickedOut(@PathVariable("sessionId") String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        if (session != null) {
            session.setAttribute(Constants.SESSION_FORCE_LOGOUT_KEY, Boolean.TRUE);
            sessionDAO.update(session);
            return new ResponseResult();
        } else {
            return new ResponseResult(BaseResponseResult.FAILURE, "获取session信息失败");
        }
    }
}
