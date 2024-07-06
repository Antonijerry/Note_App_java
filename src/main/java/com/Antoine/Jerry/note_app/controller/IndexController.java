package com.Antoine.Jerry.note_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String getHome(){
        return "home page";
    }

    @GetMapping("/test")
    public String getTest(){
        return "<h1> This is from a Test Controller </h1>";
    }

    @GetMapping("/app/admin")
    public String getAdmin(){
        return "Admin Page";
    }

    @GetMapping("/app/dashboard")
    public String getDashboard(){
        return "Dashboard page";
    }
}
