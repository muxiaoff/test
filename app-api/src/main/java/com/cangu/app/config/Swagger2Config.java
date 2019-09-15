package com.cangu.app.config;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * @author zff
 */
@Configuration
public class Swagger2Config {

    @Value("${swagger2.open}")
    public boolean open_swagger_2;
    @Value("${swagger2.testToken}")
    public String testToken;
    @Value("${swagger2.basePackage}")
    public String basePackage;
    @Value("${swagger2.name}")
    public String name;
    @Value("${swagger2.url}")
    public String url;
    @Value("${swagger2.email}")
    public String email;
    @Value("${swagger2.title}")
    public String title;
    @Value("${swagger2.description}")
    public String description;

    @Bean
    public Docket createRestApi() {
        if (!open_swagger_2) {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("XXX.XXX.XXX"))
                    .paths(PathSelectors.any())
                    .build();
        }
        ParameterBuilder token = new ParameterBuilder();
        List<Parameter> pars = Lists.newArrayList();
        token.name("Authorization").description("Bearer token")
                .defaultValue(testToken)
                .modelRef(new ModelRef("string")).parameterType("header")
                /** header中的ticket参数非必填，传空也可以 */
                .required(false).build();
        pars.add(token.build());


        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(pars)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(name,url,email);
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl("localhost:8080")
                .contact(contact)
                .version("1.0")
//                .license("debug")
                .build();
    }
}
