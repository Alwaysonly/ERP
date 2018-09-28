package com.huige.erp.ac.apis.service;

import java.util.Map;

/**
 * @Author Z.xichao
 * @Create 2018-9-20
 * @Comments
 */
public interface ShiroService {

    Map<String, String> loadFilterChainDefinitions();

    void updatePermission();
}
