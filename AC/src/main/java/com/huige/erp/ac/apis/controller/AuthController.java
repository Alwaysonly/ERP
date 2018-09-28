package com.huige.erp.ac.apis.controller;

import com.huige.erp.ac.apis.service.TAcPermissionInfoService;
import com.huige.erp.ac.configuration.AcConfiguration;
import com.huige.erp.common.aop.AppControllerLog;
import com.huige.erp.common.base.BaseController;
import com.huige.erp.common.constants.LogTypes;
import com.huige.erp.common.dto.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

/**
 * @Author Z.xichao
 * @Create 2018-8-23
 * @Comments
 */
@Controller
@RequestMapping("/auth")
@Api(tags = "2.授权控制")
public class AuthController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AcConfiguration acConfiguration;
    @Autowired
    private TAcPermissionInfoService tAcPermissionInfoService;

    @PostMapping("/login")
    @AppControllerLog(description = "用户登录", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.login)
    @ApiOperation("用户登录")
    public String doLogin(ServletRequest request, String userName, String password, Model model) {

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            model.addAttribute("msg", "用户名或密码不能为空");
            return "login";
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, DigestUtils.sha256Hex(password));
        try {
            subject.login(token);
            return "redirect:" + acConfiguration.getSuccessEndpoint();
        } catch (LockedAccountException lae) {
            token.clear();
            model.addAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            return "login";
        } catch (AuthenticationException e) {
            token.clear();
            model.addAttribute("msg", "用户或密码不正确！");
            return "login";
        }
    }


    @GetMapping("/navs")
    @ResponseBody
    @AppControllerLog(description = "导航菜单", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.select)
    @ApiOperation("导航菜单")
    public ResponseResult getNavs(ServletRequest request) {
        return new ResponseResult(tAcPermissionInfoService.getNavsTreeData());
    }

}
