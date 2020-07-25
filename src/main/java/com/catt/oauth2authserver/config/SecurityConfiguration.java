package com.catt.oauth2authserver.config;

import com.catt.oauth2authserver.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description:
 * @author: Huang Zhi Jie
 * @time: 2020/7/16 23:38
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开放所有token相关
        /*http.requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll();*/

        http.authorizeRequests().antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated() //所有请求都需要通过认证
                .and()
                .httpBasic() //Basic登录
                .and()
                .csrf().disable(); //关跨域保护
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 1 写死登录自定义用户
        // @formatter: off
        /*auth.inMemoryAuthentication()
                .withUser("zhuzhu")
                .password(passwordEncoder().encode("xiaxia"))
                .authorities(Collections.emptyList());*/
        // 2 动态绑定数据库认证
        auth.userDetailsService(userDetailsService);
        // @formatter: on
    }

}
