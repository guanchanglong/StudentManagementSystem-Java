package com.xupt.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @RequestMapping("/index")
    public ModelAndView returnIndex(Model model){
        model.addAttribute("title","登录");
        return new ModelAndView("index","index",model);
    }
}
