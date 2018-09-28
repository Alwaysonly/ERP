package com.huige.erp.common.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Author Z.xichao
 * @Create 2018-6-7
 * @Comments http://localhost:9090/swagger-ui.html
 * http://localhost:9090/doc.html
 */
@EnableSwagger2
@Configuration
public class Swagger2Configuration {

    private static final Logger logger = LoggerFactory.getLogger(Swagger2Configuration.class);

    @Bean
    public Docket oaApi() {
//        ParameterBuilder ticketPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<>();
//
//        ticketPar.name("Authorization").description("bearer token")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build(); //header中的ticket参数非必填，传空也可以
//
//        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("0.ERP_AC")
                .apiInfo(new ApiInfoBuilder()
                        .title("慧格创新科技有限公司_ERP_接口文档")
                        .description("授权中心_接口文档")
                        .contact(new Contact("Mr.Zhang", "http://zhangxichao.me", "zhenxinAngel@vip.qq.com"))
                        .version("版本号:1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huige.erp.ac"))
                .paths(PathSelectors.any())
                .build()
//                .globalOperationParameters(pars)
                ;
    }

    @Bean
    public Docket systemApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("99.ERP_SYSTEM")
                .apiInfo(new ApiInfoBuilder()
                        .title("慧格创新科技有限公司_ERP_接口文档")
                        .description("系统管理_接口文档")
                        .contact(new Contact("Mr.Zhang", "http://zhangxichao.me", "zhenxinAngel@vip.qq.com"))
                        .version("版本号:1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huige.erp.system"))
                .paths(PathSelectors.any())
                .build()
                ;
    }
}
