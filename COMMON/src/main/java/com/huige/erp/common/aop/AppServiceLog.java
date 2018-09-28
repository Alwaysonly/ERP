package com.huige.erp.common.aop;

import java.lang.annotation.*;

/**
 * @Author Z.xichao
 * @Create 2018-9-28
 * @Comments
 */

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AppServiceLog {

    String description() default "";//描述

    String moduleType() default "";//模块代码

    String operateValue() default "";//操作类型

}
