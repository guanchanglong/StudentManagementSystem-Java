package com.xupt.demo.controller;

import com.xupt.demo.entity.Student;
import com.xupt.demo.entity.StudentClass;
import com.xupt.demo.entity.Students;
import com.xupt.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ScoreService scoreService;

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
        model.addAttribute("title","管理员菜单");
        return new ModelAndView("admin/menuToAdmin","students",model);
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
        model.addAttribute("title","管理员菜单");
        return new ModelAndView("admin/searchStudentToAdmin","students",model);
    }

    @RequestMapping("/returnClassName")
    public ModelAndView returnClassName(Model model,HttpServletRequest request){
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
        modelAndView.addObject("title","管理员菜单");
        modelAndView.setViewName("admin/addStudentToAdmin");
        return modelAndView;
    }

    /**
     * 添加学生
     * 判断重复添加的条件：
     *  在一个班里面有着相同名字和性别的就是重复的
     * @param name
     * @param className
     * @param password
     * @param sex
     * @return
     */
    @RequestMapping("/addStudent")
    public ModelAndView addStudent(@RequestParam("studentName") String name,
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
        modelAndView.addObject("title","管理员菜单");
        modelAndView.setViewName("redirect:/admin/returnClassName");
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
        modelAndView.addObject("title","管理员菜单");
        modelAndView.setViewName("admin/modifyPasswordToAdmin");
        return modelAndView;
    }

    @RequestMapping("/modifyPassword")
    public ModelAndView modifyPassword(HttpServletRequest request,
                                       @RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword){
        ModelAndView modelAndView = new ModelAndView();
        String adminName = String.valueOf(request.getSession().getAttribute("userLogin"));
        //如果原密码正确的话
        if (adminService.login(adminName,oldPassword).isEmpty()){
            modelAndView.addObject("flag","原密码错误！！！");
        }else{
            int flag = adminService.modifyPassword(adminName,newPassword);
            if (flag==1){
                modelAndView.addObject("flag","修改成功");
            }else{
                modelAndView.addObject("flag","修改失败！！！");
            }
        }
        modelAndView.addObject("title","管理员菜单");
        modelAndView.setViewName("redirect:/admin/toModifyPasswordPage");
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
        modelAndView.addObject("title","管理员菜单");
        modelAndView.setViewName("redirect:/admin/showStudentList");
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
        modelAndView.addObject("title","管理员菜单");
        modelAndView.setViewName("admin/modifyStudentToAdmin");
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
        modelAndView.addObject("title","管理员菜单");
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
        modelAndView.setViewName("admin/modifyStudentToAdmin");
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
        model.addAttribute("title","管理员菜单");
        return new ModelAndView("admin/showClassToAdmin","classes",model);
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
        model.addAttribute("title","管理员菜单");
        return new ModelAndView("admin/searchClassToAdmin","classes",model);
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
        modelAndView.addObject("title","管理员菜单");
        modelAndView.setViewName("redirect:/admin/showClassList");
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
        modelAndView.addObject("title","管理员菜单");
        modelAndView.setViewName("admin/modifyClassToAdmin");
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
        modelAndView.addObject("title","管理员菜单");
        modelAndView.addObject("name",name);
        modelAndView.addObject("info",info);
        boolean flag = classService.modifyClass(id,oldName,name,info);
        if (flag){
            modelAndView.addObject("success","修改成功");
        }else{
            modelAndView.addObject("fail","修改失败！！！");
        }
        modelAndView.setViewName("admin/modifyClassToAdmin");
        return modelAndView;
    }

    @RequestMapping("/addClassToPage")
    public ModelAndView addClassToPage(){
        return new ModelAndView("admin/addClassToAdmin");
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
        modelAndView.addObject("title","管理员菜单");
        modelAndView.setViewName("admin/addClassToAdmin");
        return modelAndView;
    }

    /**
     * 进入成绩统计页面
     * @return
     */
    @RequestMapping("/scoreInfoToPage")
    public ModelAndView scoreInfoToPage(){
        ModelAndView modelAndView = new ModelAndView();
        //找出所有课程的名字
        List<String> courseList = courseService.returnAllCourseName();
        //获取到最后一个课程的名字
        String lastCourseName = courseList.get(courseList.size()-1);
        //获取对应课程id
        int id = courseService.getCourseId(lastCourseName);
        //获取对应分数
        int[] scoreList = scoreService.getMaxScoreAndMinScoreAdnAveScoreByCourseId(id);
        //如果该课程有学生信息
        if (scoreList!=null){
            modelAndView.addObject("max",scoreList[0]);
            modelAndView.addObject("min",scoreList[1]);
            modelAndView.addObject("ave",scoreList[2]);
        }
        //如果该课程没有学生信息
        else{
            modelAndView.addObject("max","无");
            modelAndView.addObject("min","无");
            modelAndView.addObject("ave","无");
        }
        //返回初始页面上的数据
        modelAndView.addObject("courseList",courseList);
        modelAndView.addObject("title","管理员菜单");
        modelAndView.addObject("type","默认显示");
        modelAndView.setViewName("/admin/scoreInfoToAdmin");
        return modelAndView;
    }

    /**
     * 返回Echarts需要用到的数据
     * @param courseName
     * @param showSelect
     * @return
     */
    @RequestMapping("/scoreInfo")
    public ModelAndView scoreInfo(@RequestParam("courseName") String courseName,
                                  @RequestParam("showSelect")String showSelect){
        ModelAndView modelAndView = new ModelAndView();
        //找出所有课程的名字
        List<String> courseList = courseService.returnAllCourseName();
        List<String> sortCourseList = new ArrayList<>();
        for(String course:courseList){
            if (!course.equals(courseName)){
                sortCourseList.add(course);
            }
        }
        sortCourseList.add(courseName);
        //获取对应课程id
        int id = courseService.getCourseId(courseName);
        if (showSelect.equals("默认显示")){
            int[] scoreList = scoreService.getMaxScoreAndMinScoreAdnAveScoreByCourseId(id);
            if (scoreList!=null){
                modelAndView.addObject("max",scoreList[0]);
                modelAndView.addObject("min",scoreList[1]);
                modelAndView.addObject("ave",scoreList[2]);
            }
            //如果该课程没有学生信息
            else{
                modelAndView.addObject("max","无");
                modelAndView.addObject("min","无");
                modelAndView.addObject("ave","无");
            }
            modelAndView.addObject("type","默认显示");
        }
        else if (showSelect.equals("柱状图")){
            int[]scoreRange = scoreService.getScoreRange(id);
            List<Integer> scoreList = new ArrayList<>();
            //如果这门课程存在成绩
            if (scoreRange!=null){
                for(int i=0;i<scoreRange.length-1;i++){
                    scoreList.add(scoreRange[i]);
                }
                modelAndView.addObject("totalNumberPeople",scoreRange[scoreRange.length-1]);
                modelAndView.addObject("scoreList",scoreList);
            }else{
                modelAndView.addObject("scoreList",null);
            }
            modelAndView.addObject("type","柱状图");
        }
        else if (showSelect.equals("饼状图")){
            int[]scoreRange = scoreService.getScoreRange(id);
            //如果这门课程存在成绩
            if (scoreRange!=null){
                modelAndView.addObject("score1",((double)scoreRange[0]/(double) scoreRange[scoreRange.length-1])*100);
                modelAndView.addObject("score2",((double)scoreRange[1]/(double) scoreRange[scoreRange.length-1])*100);
                modelAndView.addObject("score3",((double)scoreRange[2]/(double) scoreRange[scoreRange.length-1])*100);
                modelAndView.addObject("score4",((double)scoreRange[3]/(double) scoreRange[scoreRange.length-1])*100);
                modelAndView.addObject("score5",((double)scoreRange[4]/(double) scoreRange[scoreRange.length-1])*100);
                modelAndView.addObject("totalNumberPeople",scoreRange[scoreRange.length-1]);
            }
            else{
                modelAndView.addObject("score1",null);
            }
            modelAndView.addObject("type","饼状图");
        }

        modelAndView.addObject("courseList",sortCourseList);
        modelAndView.addObject("title","管理员菜单");
        modelAndView.setViewName("/admin/scoreInfoToAdmin");
        return modelAndView;
    }
}
