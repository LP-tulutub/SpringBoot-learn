package com.lp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/aop")
public class AopController {

    @RequestMapping("/test1")
    @ResponseBody
    public String test1(){
        System.out.println("controller---test1");
        return "test1";
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String test2(){
        System.out.println("controller---test2");
        return "test2";
    }

}
