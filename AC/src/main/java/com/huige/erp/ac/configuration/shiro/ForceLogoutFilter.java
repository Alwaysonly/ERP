package com.huige.erp.ac.configuration.shiro;

import com.alibaba.fastjson.JSON;
import com.huige.erp.common.constants.Constants;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Z.xichao
 * @Create 2018-9-26
 * @Comments
 */
public class ForceLogoutFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Session session = getSubject(request, response).getSession();
        if (session == null) {
            return true;
        }
        return session.getAttribute(Constants.SESSION_FORCE_LOGOUT_KEY) == null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        getSubject(request, response).logout();//强制退出
        String loginUrl = getLoginUrl() + (getLoginUrl().contains("?") ? "&" : "?") + "forceLogout=1";
        Map<String, Object> resultMap = new HashMap<>();
        if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {
            resultMap.put("code", 301);
            resultMap.put("message", "您已经被管理员强制退出，请重新登录!");
            resultMap.put("data", loginUrl);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(resultMap));
            out.flush();
            out.close();
        } else {
            WebUtils.issueRedirect(request, response, loginUrl);
        }
        return false;
    }
}
