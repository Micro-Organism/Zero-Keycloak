package com.zero.keycloak.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/keycloak")
public class KeycloakController {

    @GetMapping(path = "/")
    public HashMap<String, Object> index() {
        // get a successful user login
        OAuth2User user = ((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new HashMap<>() {{
            put("hello", user.getAttribute("name"));
            put("your email is", user.getAttribute("email"));
        }};
    }

    @RequestMapping("/hello")
    public Map<String, Object> showHelloWorld() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "HelloWorld");
        return map;
    }

    @GetMapping("getValue")
    public String getValue() {
        return "Hello Keycloak!";
    }

    @GetMapping(path = "/unauthenticated")
    public HashMap<String, Object> unauthenticatedRequests() {
        return new HashMap<>(){{
            put("this is ", "unauthenticated endpoint");
        }};
    }

}