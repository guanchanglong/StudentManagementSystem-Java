package com.xupt.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartController {
    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
