package com.lp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String welcome(){
        System.out.println("Welcome...reload2");
        return "index";
    }

    @RequestMapping("{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

}
