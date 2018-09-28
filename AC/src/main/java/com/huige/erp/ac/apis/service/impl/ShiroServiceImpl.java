package com.huige.erp.ac.apis.service.impl;

import com.huige.erp.ac.apis.service.ShiroService;
import com.huige.erp.ac.apis.service.TAcPermissionInfoService;
import com.huige.erp.ac.configuration.AcConfiguration;
import com.huige.erp.ac.pojo.dto.BeanPermissionInjection;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Z.xichao
 * @Create 2018-9-20
 * @Comments
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    private final Logger logger = LoggerFactory.getLogger(ShiroServiceImpl.class);

    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    private TAcPermissionInfoService tAcPermissionInfoService;

    @Autowired
    private AcConfiguration acConfiguration;

    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<BeanPermissionInjection> permissionInjections = tAcPermissionInfoService.getPermissionInjections();

        for (String url : acConfiguration.getUrlWhiteList()) {
            filterChainDefinitionMap.put(url, "anon");
        }
        for (BeanPermissionInjection injection : permissionInjections) {
            filterChainDefinitionMap.put(injection.getUrl(), "perms[" + injection.getCode() + "],roles[" + injection.getPositionCode() + "]");
            logger.debug("初始化权限: 资源路径[{}],权限值[{}],职位权限[{}]", injection.getUrl(), "perms[" + injection.getCode() + "]", "role[" + injection.getPositionCode() + "]");
        }
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/auth/kickout", "anon");
        filterChainDefinitionMap.put("/auth/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc,kickout,forceLogout");

        return filterChainDefinitionMap;
    }

    @Override
    public void updatePermission() {

        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            // 清空老的权限控制
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim()
                        .replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
            logger.info("更新权限成功!");
        }
    }
}
