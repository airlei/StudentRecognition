package com.projects.modular.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.TeacherClass;
import com.projects.modular.attendance.model.params.TeacherClassParam;
import com.projects.modular.attendance.service.TeacherClassService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;


/**
 * 教师管理班级控制器
 *
 * @author demo
 * @Date 2022-05-11 23:51:00
 */
@Controller
@RequestMapping("/teacherClass")
public class TeacherClassController extends BaseController {

    private String PREFIX = "/modular/teacherClass";

    @Autowired
    private TeacherClassService teacherClassService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/teacherClass.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/add")
    public String add(Model model ,Long teacherId) {
    	model.addAttribute("teacherId", teacherId);
        return PREFIX + "/teacherClass_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/teacherClass_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(TeacherClassParam teacherClassParam) {
        this.teacherClassService.add(teacherClassParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(TeacherClassParam teacherClassParam) {
        this.teacherClassService.update(teacherClassParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(TeacherClassParam teacherClassParam) {
        this.teacherClassService.delete(teacherClassParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(TeacherClassParam teacherClassParam) {
        TeacherClass detail = this.teacherClassService.getById(teacherClassParam.getTeacherClassId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author demo
     * @Date 2022-05-11
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(TeacherClassParam teacherClassParam) {
        return this.teacherClassService.findPageBySpec(teacherClassParam);
    }

}


