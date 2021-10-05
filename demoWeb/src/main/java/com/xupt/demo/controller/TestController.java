package com.xupt.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/echarts")
    public String myECharts(Model model){

        String skirt = "裙子";
        int nums = 30;
        List<Integer> scoreList = new ArrayList<>();
        scoreList.add(10);
        scoreList.add(20);
        scoreList.add(30);
        scoreList.add(40);
        scoreList.add(10);
        model.addAttribute("totalNumberPeople",110);
        model.addAttribute("scoreList", scoreList);
        return "admin/scoreInfoToAdmin";
    }
}
