package com.catt.oauth2authserver.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String selectPasswdByEmail(String userName);

    String selectPasswdByPhone(String userName);
}
