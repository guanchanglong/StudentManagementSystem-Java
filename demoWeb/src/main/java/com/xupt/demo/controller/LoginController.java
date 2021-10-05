package com.xupt.demo.controller;

import com.xupt.demo.service.AdminService;
import com.xupt.demo.service.StudentService;
import com.xupt.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;


    /**
     * 创建登录表单
     * @param model
     * @return
     */
    @GetMapping("/login")
    public ModelAndView createFormForLogin(Model model){
        model.addAttribute("title","用户登录");
        //设置跳转到的页面和该页面的标题
        return new ModelAndView("index","title",model);
    }


    /**
     * 用户登录
     * @param username  用户名
     * @param password  密码
     * @param tryCode 输入的验证码
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/userLogin")
    public ModelAndView userLogin(@RequestParam(value = "username") String username,
                                  @RequestParam(value = "password") String password,
                                  @RequestParam(value = "tryCode") String tryCode,
                                  HttpSession session,Model model, HttpServletRequest request) {
        String userType = request.getParameter("userType");
        boolean flag = false;
        //系统管理员
        if (userType.equals("1")){
            if (!adminService.login(username,password).isEmpty()){
                flag = true;
            }
        }
        //教师
        else if (userType.equals("2")){
            if (!teacherService.login(username,password).isEmpty()){
                flag = true;
            }
        }
        //学生
        else if (userType.equals("3")){
            if (!studentService.login(username,password).isEmpty()){
                flag = true;
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        if (!flag){
            modelAndView.addObject("info", "用户名或密码错误");
            modelAndView.setViewName("index");
            return modelAndView;
        }
        else{
            //正确的验证码
            String rightCode = (String) request.getSession().getAttribute("rightCode");
            //在控制台上打印出两个验证码进行对比
            System.out.println("正确的验证码:" + rightCode + " ———— 输入的验证码:" + tryCode);
            //如果两个验证码并不相等，则进入错误阶段
            if (!rightCode.equals(tryCode)) {
                modelAndView.addObject("info", "验证码错误,请再输一次!");
                //验证码错误的话直接回到userLogin这个页面，并将错误信息info传输到该页面
                modelAndView.setViewName("index");
                return modelAndView;
            }
            //如果两个验证码相等，则重定向到主菜单页面
            else {
                //在session中设置用户信息
                session.setAttribute("userLogin",username);
                int type = Integer.parseInt(userType);
                //登录成功直接重定向到主菜单
                switch (type){
                    case 1:
                        return new ModelAndView("redirect:/admin/showStudentList");
                    case 2:
                        return new ModelAndView("redirect:/teacher/showStudentList");
                    case 3:
                        return new ModelAndView("redirect:/student/showStudentList");
                    default:
                        modelAndView.setViewName("index");
                        return modelAndView;
                }
            }
        }
    }

    /*
        在退出功能的方法里面我们就要执行清空session中数据的操作，
        首先用Enumeration的对象来得到所有的session中的信息，
        使用循环遍历，执行 removeAttribute（）的session删除操作，
        清空所有session中的信息，最后再跳转到登录页面：
    */
    @RequestMapping("/outLogin")
    public ModelAndView outLogin(HttpServletRequest request){
        Enumeration em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        //重定向回index页面
        return new ModelAndView("redirect:/");
    }
}
