package com.huige.erp.common.apis.controller;


import com.huige.erp.common.aop.AppControllerLog;
import com.huige.erp.common.constants.LogTypes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.huige.erp.common.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;

/**
 * @author Mr.Zhang
 * @since 2018-09-28
 */
@Controller
@RequestMapping("/common")
@Api(tags = "1.公共模块")
public class CommonController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

}

