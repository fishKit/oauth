package com.catt.oauth2authserver.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Huang Zhi Jie
 * @time: 2020/7/23 14:10
 */
@RestController
@RequestMapping("/account")
public class RestfulApiProviderController {

    @RequestMapping("/info/{account}")
    public String info(@PathVariable("account") String account) {
        return "55";
    }

    @RequestMapping("child/{account}")
    public String child(@PathVariable("account") String qq) {
        return "56";
    }
}