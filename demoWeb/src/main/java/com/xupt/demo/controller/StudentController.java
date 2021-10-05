package com.xupt.demo.controller;

import com.xupt.demo.entity.Students;
import com.xupt.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 展示所有学生信息
     * @param model
     * @return
     */
    @RequestMapping("/showStudentList")
    public ModelAndView showStudentList(Model model,HttpServletRequest request){
        String name = String.valueOf(request.getSession().getAttribute("userLogin"));
        System.out.println("name="+name);
        List<Students> studentsList = studentService.findStudentsByName(name);
        model.addAttribute("studentsList" , studentsList);
        model.addAttribute("title","学生菜单");
        return new ModelAndView("student/menuToStudent","students",model);
    }

    /**
     * 搜索学生信息
     * @param model
     * @param name
     * @return
     */
    @RequestMapping("/findStudentByName")
    public ModelAndView findStudentByName(Model model,@RequestParam("studentName") String name){
        List<Students> list = studentService.findStudentsByName(name);
        model.addAttribute("studentsList" , list);
        model.addAttribute("title","学生菜单");
        return new ModelAndView("student/searchStudentToStudent","students",model);
    }

    @RequestMapping("/toModifyPasswordPage")
    public ModelAndView toModifyPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        String flag = request.getParameter("flag");
        if (flag!=null){
            if (flag.equals("原密码错误！！！")){
                modelAndView.addObject("fail",flag);
            }else if (flag.equals("修改失败！！！")){
                modelAndView.addObject("fail",flag);
            }else if (flag.equals("修改成功")){
                modelAndView.addObject("success",flag);
            }
        }
        modelAndView.addObject("title","学生菜单");
        modelAndView.setViewName("student/modifyPasswordToStudent");
        return modelAndView;
    }

    /**
     * 修改密码
     * @param request
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping("/modifyPassword")
    public ModelAndView modifyPassword(HttpServletRequest request,
                                       @RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword){
        ModelAndView modelAndView = new ModelAndView();
        String teacherName = String.valueOf(request.getSession().getAttribute("userLogin"));
        //如果原密码正确的话
        if (studentService.login(teacherName,oldPassword).isEmpty()){
            modelAndView.addObject("flag","原密码错误！！！");
        }else{
            boolean flag = studentService.modifyPassword(teacherName,newPassword);
            if (flag){
                modelAndView.addObject("flag","修改成功");
            }else{
                modelAndView.addObject("flag","修改失败！！！");
            }
        }
        modelAndView.setViewName("redirect:/student/toModifyPasswordPage");
        return modelAndView;
    }
}
