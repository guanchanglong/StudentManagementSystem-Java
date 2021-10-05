package com.xupt.demo.controller;

import com.xupt.demo.entity.Student;
import com.xupt.demo.entity.StudentClass;
import com.xupt.demo.entity.Students;
import com.xupt.demo.service.ClassService;
import com.xupt.demo.service.StudentService;
import com.xupt.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    /**
     * 展示所有学生信息
     * @param model
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/showStudentList")
    public ModelAndView showStudentList(Model model,
                                        @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        List<Student> studentsList = studentService.findAll();
        Page<Students> studentsPage = studentService.getStudentsListPage(pageNum, pageSize);
        model.addAttribute("studentsPage", studentsPage);
        model.addAttribute("studentsList" , studentsList);
        model.addAttribute("title","教师菜单");
        return new ModelAndView("teacher/menuToTeacher","students",model);
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
        model.addAttribute("title","教师菜单");
        return new ModelAndView("teacher/searchStudentToTeacher","students",model);
    }

    @RequestMapping("/returnClassName")
    public ModelAndView returnClassName(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        List<String> list = studentService.returnClassName();
        String flag = String.valueOf(request.getParameter("flag"));
        System.out.println("flag="+flag);
        if (flag!=null){
            if (flag.equals("添加成功")){
                modelAndView.addObject("success",flag);
            }else if (flag.equals("添加失败，该学生已存在！！！")){
                modelAndView.addObject("fail",flag);
            }
        }
        modelAndView.addObject("classList",list);
        modelAndView.addObject("title","教师菜单");
        modelAndView.setViewName("teacher/addStudentToTeacher");
        return modelAndView;
    }

    @RequestMapping("/addStudent")
    public ModelAndView addStudent(Model model,
                                   @RequestParam("studentName") String name,
                                   @RequestParam("className") String className,
                                   @RequestParam("studentPassword") String password,
                                   @RequestParam("sex") String sex){
        ModelAndView modelAndView = new ModelAndView();
        boolean flag = studentService.findStudentByNameAndClassAndSex(name,className,sex,password);
        if (flag){
            modelAndView.addObject("flag", "添加成功");
        }else{
            modelAndView.addObject("flag", "添加失败，该学生已存在！！！");
        }
        modelAndView.setViewName("redirect:/teacher/returnClassName");
        return modelAndView;
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
        modelAndView.addObject("title","教师菜单");
        modelAndView.setViewName("teacher/modifyPasswordToTeacher");
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
        if (teacherService.login(teacherName,oldPassword).isEmpty()){
            modelAndView.addObject("flag","原密码错误！！！");
        }else{
            int flag = teacherService.modifyPassword(teacherName,newPassword);
            if (flag==1){
                modelAndView.addObject("flag","修改成功");
            }else{
                modelAndView.addObject("flag","修改失败！！！");
            }
        }
        modelAndView.setViewName("redirect:/teacher/toModifyPasswordPage");
        return modelAndView;
    }

    /**
     * 删除学生
     * @param name
     * @param className
     * @param sex
     * @return
     */
    @RequestMapping("/deleteStudent")
    public ModelAndView deleteStudent(@RequestParam("name")String name,
                                      @RequestParam("className") String className,
                                      @RequestParam("sex") String sex){
        ModelAndView modelAndView = new ModelAndView();
        int classId = classService.returnClassId(className);
        boolean flag = studentService.deleteStudent(name,className,classId,sex);
        modelAndView.setViewName("redirect:/teacher/showStudentList");
        return modelAndView;
    }


    /**
     * 修改学生信息
     * @param name
     * @param className
     * @param sex
     * @param password
     * @return
     */
    @RequestMapping("/modifyStudentToPage")
    public ModelAndView modifyStudentToPage(@RequestParam("name")String name,
                                            @RequestParam("className") String className,
                                            @RequestParam("sex") String sex,
                                            @RequestParam("password")String password){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name",name);
        modelAndView.addObject("className",className);
        modelAndView.addObject("sex",sex);
        modelAndView.addObject("password",password);
        List<String> list = studentService.returnClassName();
        modelAndView.addObject("classList",list);
        modelAndView.addObject("title","教师菜单");
        modelAndView.setViewName("teacher/modifyStudentToTeacher");
        return modelAndView;
    }

    @RequestMapping("/modifyStudent")
    public ModelAndView modifyStudent(@RequestParam("oldName") String oldName,
                                      @RequestParam("oldClassName") String oldClassName,
                                      @RequestParam("studentName") String name,
                                      @RequestParam("className") String className,
                                      @RequestParam("sex") String sex,
                                      @RequestParam("studentPassword") String password){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("name="+name);
        System.out.println("className="+className);
        System.out.println("sex="+sex);
        System.out.println("password="+password);
        modelAndView.addObject("title","教师菜单");
        List<String> list = studentService.returnClassName();
        modelAndView.addObject("classList",list);
        modelAndView.addObject("className",className);
        modelAndView.addObject("sex",sex);
        boolean flag = studentService.modifyStudent(oldName,oldClassName,name,className,sex,password);
        if (flag){
            modelAndView.addObject("success","修改成功");
        }else{
            modelAndView.addObject("fail","修改失败！！！");
        }
        modelAndView.setViewName("teacher/modifyStudentToTeacher");
        return modelAndView;
    }


    @RequestMapping("/showClassList")
    public ModelAndView showClassList(Model model,
                                      @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        List<StudentClass> classesList = classService.findAll();
        Page<StudentClass> classesPage = classService.getClassesListPage(pageNum, pageSize);
        model.addAttribute("classesPage", classesPage);
        model.addAttribute("classesList" , classesList);
        model.addAttribute("title","教师菜单");
        return new ModelAndView("teacher/showClassToTeacher","classes",model);
    }

    /**
     * 搜索班级信息
     * @param model
     * @param name
     * @return
     */
    @RequestMapping("/findClassByName")
    public ModelAndView findClassByName(Model model,@RequestParam("className") String name){
        List<StudentClass> list = classService.findAllClassByName(name);
        model.addAttribute("classesList" , list);
        model.addAttribute("title","教师菜单");
        return new ModelAndView("teacher/searchClassToTeacher","classes",model);
    }

    /**
     * 删除班级信息
     * 同时删除对应班级下的学生
     * @param id
     * @return
     */
    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(@RequestParam("id")int id,
                                    @RequestParam("name")String name){
        ModelAndView modelAndView = new ModelAndView();
        boolean flag = classService.deleteClassByIdAndName(id,name);
        modelAndView.setViewName("redirect:/teacher/showClassList");
        return modelAndView;
    }

    /**
     * 进入修改班级页面
     * @param id
     * @param name
     * @param info
     * @return
     */
    @RequestMapping("/modifyClassToPage")
    public ModelAndView modifyClassToPage(@RequestParam("id")int id,
                                          @RequestParam("name") String name,
                                          @RequestParam("info") String info){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id",id);
        modelAndView.addObject("name",name);
        modelAndView.addObject("info",info);
        modelAndView.addObject("title","教师菜单");
        modelAndView.setViewName("teacher/modifyClassToTeacher");
        return modelAndView;
    }

    /**
     * 提交待修改班级的信息
     * @param id
     * @param oldName
     * @param name
     * @param info
     * @return
     */
    @RequestMapping("/modifyClass")
    public ModelAndView modifyClass(@RequestParam("id") int id,
                                    @RequestParam("oldName") String oldName,
                                    @RequestParam("className") String name,
                                    @RequestParam("classInfo") String info){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","教师菜单");
        modelAndView.addObject("name",name);
        modelAndView.addObject("info",info);
        boolean flag = classService.modifyClass(id,oldName,name,info);
        if (flag){
            modelAndView.addObject("success","修改成功");
        }else{
            modelAndView.addObject("fail","修改失败！！！");
        }
        modelAndView.setViewName("teacher/modifyClassToTeacher");
        return modelAndView;
    }

    @RequestMapping("/addClassToPage")
    public ModelAndView addClassToPage(){
        return new ModelAndView("teacher/addClassToTeacher");
    }

    @RequestMapping("/addClass")
    public ModelAndView addClass(@RequestParam("className") String className,
                                 @RequestParam("classInfo") String classInfo){
        ModelAndView modelAndView = new ModelAndView();
        boolean flag = classService.addClass(className,classInfo);
        if (flag){
            modelAndView.addObject("success", "添加成功");
        }else{
            modelAndView.addObject("fail", "添加失败，该班级已存在！！！");
        }
        modelAndView.setViewName("teacher/addClassToTeacher");
        return modelAndView;
    }

}
