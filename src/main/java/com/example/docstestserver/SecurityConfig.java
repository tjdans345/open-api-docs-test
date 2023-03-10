package com.example.docstestserver;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] SWAGGER_WHITELIST = {
        "/api-docs/**", "/test/**", "/v3/api-docs*/**", "/swagger*/**", "/ultari-docs.html/**", "/api-docs/ultari/**"
    };

    /**
     * 정적 리소스 파일 필터 체인 설정 메서드
     *
     * @param httpSecurity HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception Exception
     */
    @Bean
    public SecurityFilterChain resources(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .formLogin().disable()
                .authorizeHttpRequests(authorize ->
                        authorize
                                .shouldFilterAllDispatcherTypes(false)
                                .requestMatchers(SWAGGER_WHITELIST).permitAll()
                                .anyRequest().authenticated()
                );
//                .requestCache().disable()
//                .securityContext().disable()
//                .sessionManagement().disable();
        return httpSecurity.build();
    }


}
