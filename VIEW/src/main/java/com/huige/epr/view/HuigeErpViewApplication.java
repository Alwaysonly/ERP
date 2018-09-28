package com.huige.epr.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.huige.erp"})
public class HuigeErpViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuigeErpViewApplication.class, args);
    }

}
