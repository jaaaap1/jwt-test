package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello Admin!";
    }

    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello User!";
    }

    @GetMapping("/permission/hello")
    public String permissionHello() {
        return "Hello with endpoint permission!";
    }
}