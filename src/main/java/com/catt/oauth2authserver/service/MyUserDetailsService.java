package com.catt.oauth2authserver.service;

import com.catt.oauth2authserver.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Fant.J.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("请求登陆用户名:" + username);
        // 查询数据库操作
        List<Map<Object, Object>> list = userMapper.selectUserInfoByMyWrapper(username);

        if (CollectionUtils.isEmpty(list)) {
            throw new UsernameNotFoundException("the user is not found");
            // 用户角色也应在数据库中获取
            //return new org.springframework.security.core.userdetails.User(username, null, null);
        } else {
            // 用户角色也应在数据库中获取
            String roles = (String) list.get(0).get("S_ROLES");
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (String role : roles.split(",")) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            // 线上环境应该通过用户名查询数据库获取加密后的密码
            String password = (String) list.get(0).get("S_PASSWORD");
            password = "{bcrypt}" + bCryptPasswordEncoder.encode(password);
            return new org.springframework.security.core.userdetails.User(username, password, authorities);
        }
    }
}
