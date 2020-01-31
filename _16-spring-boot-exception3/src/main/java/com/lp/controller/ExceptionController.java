package com.lp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {

    @RequestMapping("/show")
    public String showInfo(){
        String str = null;
        str.length();
        return "index";
    }

    @RequestMapping("/show2")
    public String showInfo2(){
        int a = 10/0;
        return "index";
    }

}
