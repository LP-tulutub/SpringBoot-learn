package com.lp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/aop2")
public class Aop2Controller {

    @RequestMapping("/test3")
    @ResponseBody
    public String test3(@RequestParam(name = "value") int value){
        System.out.println("controller---test3: " + value);
        return "test3";
    }

    @RequestMapping("/test4")
    @ResponseBody
    public String test4(int value){
        System.out.println("controller---test4: " + value);
        return "test4";
    }

}
