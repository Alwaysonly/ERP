package com.huige.erp.ac.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Z.xichao
 * @Create 2018-8-23
 * @Comments
 */
@Component
@ConfigurationProperties(prefix = "com.huige.erp.ac")
@EnableConfigurationProperties
public class AcConfiguration {
    private String loginEndpoint = "/login";
    private String successEndpoint = "/index";
    private String kickoutEndpoint = "/auth/kickout";
    private String[] urlWhiteList;

    public String getLoginEndpoint() {
        return loginEndpoint;
    }

    public void setLoginEndpoint(String loginEndpoint) {
        this.loginEndpoint = loginEndpoint;
    }

    public String getSuccessEndpoint() {
        return successEndpoint;
    }

    public void setSuccessEndpoint(String successEndpoint) {
        this.successEndpoint = successEndpoint;
    }

    public String[] getUrlWhiteList() {
        return urlWhiteList;
    }

    public void setUrlWhiteList(String[] urlWhiteList) {
        this.urlWhiteList = urlWhiteList;
    }

    public String getKickoutEndpoint() {
        return kickoutEndpoint;
    }

    public void setKickoutEndpoint(String kickoutEndpoint) {
        this.kickoutEndpoint = kickoutEndpoint;
    }
}
