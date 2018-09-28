package com.huige.erp.common.aop;

import java.lang.annotation.*;

/**
 * @Author Z.xichao
 * @Create 2018-9-28
 * @Comments 控制层日志注解
 */

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AppControllerLog {

    String description() default "";//描述

    String moduleType() default "";//模块代码

    String operateValue() default "";//操作类型

    boolean firstParamName() default false;

}
