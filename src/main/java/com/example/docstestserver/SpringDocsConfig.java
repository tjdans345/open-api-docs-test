package com.example.docstestserver;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class SpringDocsConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("울타리 Rest API 문서") // docs title
//                .version(springDocsVersion) // docs version
                .description("울타리 Rest API 문서입니다. 잘못된 부분 또는 오류 발생 시 개발자에게 문의 부탁드립니다. ")
                .contact(
                        new Contact()
                                .name("Meteor")
                                .email("tjdans34568@gmail.com")
                                .url("soft-cone.io")
                );// 연락처

        // 시큐리티 스키마 설정
        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);

        // Security 요청 설정
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList("JWT");;

        return new OpenAPI()
                // Security 인증 컴포넌트 설정
                .components(new Components().addSecuritySchemes("JWT", bearerAuth))
                .addSecurityItem(securityRequirement)
                .info(info);
    }



}
