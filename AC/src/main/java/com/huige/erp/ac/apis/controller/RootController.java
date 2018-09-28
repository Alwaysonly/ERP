package com.huige.erp.ac.apis.controller;

import com.huige.erp.ac.apis.service.TAcPermissionInfoService;
import com.huige.erp.ac.configuration.AcConfiguration;
import com.huige.erp.ac.pojo.po.TAcPermissionInfo;
import com.huige.erp.ac.pojo.po.TAcUserInfo;
import com.huige.erp.common.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import java.util.List;

/**
 * @Author Z.xichao
 * @Create 2018-8-23
 * @Comments
 */
@Controller
@RequestMapping
public class RootController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private AcConfiguration acConfiguration;

    @Autowired
    private TAcPermissionInfoService tAcPermissionInfoService;

    @GetMapping("/")
    public String root() {
        return "redirect:" + acConfiguration.getLoginEndpoint();
    }

    @GetMapping("/login")
    public String loginView(ServletRequest req, Model model) {
        if (req.getParameter("forceLogout") != null) {
            model.addAttribute("msg", "您已经被管理员强制退出，请重新登录");
        }
        if (req.getParameter("kickout") != null) {
            model.addAttribute("msg", "您已经在其他地方登录，请重新登录！");
        }
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            return "redirect:" + acConfiguration.getSuccessEndpoint();
        }
        return "login";
    }

    @GetMapping("/index")
    public String defaultView(Model model) {
        // 用户信息
        TAcUserInfo userInfo = (TAcUserInfo) SecurityUtils.getSubject().getPrincipal();
        if (userInfo == null) return "redirect:" + acConfiguration.getLoginEndpoint();

        model.addAttribute("username", userInfo.getUserName());

        // 顶级菜单
        List<TAcPermissionInfo> topLevelMenus = tAcPermissionInfoService.getTopLevelMenus();
        model.addAttribute("topLevelMenus", topLevelMenus);

        return "index";
    }

}
