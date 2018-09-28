package com.huige.erp.system.apis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huige.erp.common.constants.Constants;
import com.huige.erp.common.dto.BaseResponseResult;
import com.huige.erp.common.dto.ResponseResult;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * @Author Z.xichao
 * @Create 2018-9-25
 * @Comments
 */
@RestController
@RequestMapping("/page/system")
public class SystemController {

    private final Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private SessionDAO sessionDAO;

    @GetMapping("/session")
    public ModelAndView sessionView() {
        return new ModelAndView("page/system/session");
    }

    @GetMapping("/sessions/list")
    public ResponseResult sessionList() {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return new ResponseResult<>(sessions);
    }

    @GetMapping("/sessions/kickedout/{sessionId}")
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
