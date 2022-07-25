package com.mic.client;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/user")
    public OAuth2User user(@AuthenticationPrincipal OAuth2User user) {
        return user;
    }
    
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}