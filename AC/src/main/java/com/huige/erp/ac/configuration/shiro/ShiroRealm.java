package com.huige.erp.ac.configuration.shiro;


import com.huige.erp.ac.apis.service.TAcPermissionInfoService;
import com.huige.erp.ac.apis.service.TAcPositionInfoService;
import com.huige.erp.ac.apis.service.TAcUserInfoService;
import com.huige.erp.ac.pojo.po.TAcUserInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @Author Z.xichao
 * @Create 2018-6-7
 * @Comments
 */
public class ShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);


    @Autowired
    private TAcPositionInfoService tAcPositionInfoService;
    @Autowired
    private TAcPermissionInfoService tAcPermissionInfoService;
    @Autowired
    private TAcUserInfoService tAcUserInfoService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof TAcUserInfo) {
            TAcUserInfo userLogin = (TAcUserInfo) principal;
            Set<String> roles = tAcPositionInfoService.findAllPositionCodeByUserId(userLogin.getId());
            authorizationInfo.addRoles(roles);

            Set<String> permissions = tAcPermissionInfoService.findAllPermissionsByUserId(userLogin.getId());
            authorizationInfo.addStringPermissions(permissions);
        }
        logger.info("获取到以下权限");
        logger.info(authorizationInfo.getStringPermissions()!=null?authorizationInfo.getStringPermissions().toString():"未获取到任何权限");
        logger.info(authorizationInfo.getStringPermissions()!=null?authorizationInfo.getRoles().toString():"未获取到任何职位");
        logger.info("---------------- Shiro 权限获取成功 ----------------------");
        return authorizationInfo;
    }

    /**
     * 认证信息(身份验证)
     *
     * @param authenticationToken 用来验证用户身份
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        // 从数据库获取对应用户信息
        TAcUserInfo tAcUserInfo = tAcUserInfoService.getUserLogin(name,password);
        if (tAcUserInfo != null) {
            // 用户为禁用状态
            if (tAcUserInfo.getLocked()) {
                throw new LockedAccountException();
            }
            if (tAcUserInfo.getDeleted()) {
                throw new DisabledAccountException();
            }
            logger.info("---------------- Shiro 凭证认证成功 ----------------------");
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    tAcUserInfo, //用户
                    tAcUserInfo.getUserPassword(), //密码
                    getName()  //realm name
            );
            return authenticationInfo;
        }
        throw new UnknownAccountException();
    }


}
