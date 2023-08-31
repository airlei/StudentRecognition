package com.projects.modular.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.Classes;
import com.projects.modular.attendance.entity.Student;
import com.projects.modular.attendance.model.params.StudentParam;
import com.projects.modular.attendance.service.StudentService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;


/**
 * 学生管理控制器
 *
 * @author demo
 * @Date 2022-05-12 10:35:01
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

    private String PREFIX = "/modular/student";

    @Autowired
    private StudentService studentService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2022-05-12
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/student.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2022-05-12
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/student_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2022-05-12
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/student_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2022-05-12
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(StudentParam studentParam) {
        this.studentService.add(studentParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author demo
     * @Date 2022-05-12
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(StudentParam studentParam) {
        this.studentService.update(studentParam);
        return ResponseData.success();
    }

    @RequestMapping("/getList")  
    @ResponseBody
    public ResponseData getList(Long majorId) {
    	 QueryWrapper<Student> objectQueryWrapper = new QueryWrapper<>();
    	
    	 List<Student> list = studentService.getBaseMapper().selectList(objectQueryWrapper);
    	 
        return ResponseData.success(list);
    }
    
    
    
    @RequestMapping("/getLists")  
    @ResponseBody
    public ResponseData getLists(Long majorId) {
    	 QueryWrapper<Student> objectQueryWrapper = new QueryWrapper<>();
    	 objectQueryWrapper.eq("classes_id", majorId);
    	 List<Student> list = studentService.getBaseMapper().selectList(objectQueryWrapper);
    	 
        return ResponseData.success(list);
    }
    /**
     * 删除接口
     *
     * @author demo
     * @Date 2022-05-12
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(StudentParam studentParam) {
        this.studentService.delete(studentParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author demo
     * @Date 2022-05-12
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(StudentParam studentParam) {
        Student detail = this.studentService.getById(studentParam.getStudentId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author demo
     * @Date 2022-05-12
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(StudentParam studentParam) {
        return this.studentService.findPageBySpec(studentParam);
    }

}


