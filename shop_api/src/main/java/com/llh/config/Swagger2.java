package com.llh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Spring可以扫描到
@Configuration
@EnableSwagger2
public class Swagger2 {

    // http://localhost:8088/swagger-ui.html   原路径查看接口

    // http://localhost:8088/doc.html   进行换肤

    //配置Swagger2核心配置 docket
    @Bean
    public Docket createRestApi() {
        //指定api类型为Swagger2
        return new Docket(DocumentationType.SWAGGER_2)  //指定api类型为Swagger2
                .apiInfo(apiInfo())                     // 定义api文档汇总信息
                .select().apis(RequestHandlerSelectors
                                 .basePackage("com.llh.api"))  // 扫描controller包（接口文档）
                .paths(PathSelectors.any())               //所有controller 都生成文档
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("天天商场") //文档页标题
                .contact(new Contact("imooc", "http://localhost", "178"))
                .description("api文档")
                .version("1.0").build();
    }
}
