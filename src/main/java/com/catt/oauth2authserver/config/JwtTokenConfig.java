package com.catt.oauth2authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Huang Zhi Jie
 * @time: 2020/7/17 1:37
 */
@Configuration
public class JwtTokenConfig {

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * token生成处理：指定签名
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                String grantType = authentication.getOAuth2Request().getGrantType();
                //授权码和密码模式才自定义token信息
                String userName = authentication.getUserAuthentication().getName();
                //role
                Collection<GrantedAuthority> roleList = authentication.getAuthorities();
                // 自定义一些token 信息
                Map<String, Object> additionalInformation = new HashMap<String, Object>(16);
                additionalInformation.put("user_name", userName);
                additionalInformation.put("我的juju", "xiaxia");
                additionalInformation.put("roleList", roleList);
                additionalInformation = Collections.unmodifiableMap(additionalInformation);
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                OAuth2AccessToken token = super.enhance(accessToken, authentication);
                return token;
            }
        };
        accessTokenConverter.setSigningKey("xiaxia");
        return accessTokenConverter;
    }
}