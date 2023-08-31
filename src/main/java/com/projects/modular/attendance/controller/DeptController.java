package com.projects.modular.attendance.controller;

import java.util.ArrayList;
import java.util.List;

import com.projects.core.common.exception.BizExceptionEnum;
import org.apache.poi.hpsf.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.core.shiro.ShiroKit;
import com.projects.modular.attendance.entity.Classes;
import com.projects.modular.attendance.entity.Major;
import com.projects.modular.attendance.entity.TeacherClass;
import com.projects.modular.attendance.model.params.ClassesParam;
import com.projects.modular.attendance.model.result.ClassesResult;
import com.projects.modular.attendance.service.ClassesService;
import com.projects.modular.attendance.service.CollegeService;
import com.projects.modular.attendance.service.MajorService;
import com.projects.modular.attendance.service.TeacherClassService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;


/**
 * 班级表控制器
 *
 * @author demo
 * @Date 2022-04-22 15:53:22
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

    private String PREFIX = "/modular/dept";

    @Autowired
    private ClassesService classesService;
    
    @Autowired
    private CollegeService collegeService;
    
    @Autowired
    private MajorService majorService;
    @Autowired
    private TeacherClassService teacherClassService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/dept.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/dept_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/dept_edit.html";
    }
    @RequestMapping("/getList")  
    @ResponseBody
    public ResponseData getList(Long majorId) {
    	 QueryWrapper<Classes> objectQueryWrapper = new QueryWrapper<>();
    	 if(majorId!=null){
             objectQueryWrapper.eq("major_id", majorId);
         }
    	 List<Classes> list = classesService.getBaseMapper().selectList(objectQueryWrapper);
    	 
        return ResponseData.success(list);
    }
    
    
    @RequestMapping("/getList2")  
    @ResponseBody
    public ResponseData getList2() {
    	
    	 QueryWrapper<TeacherClass> objectQueryWrapper1 = new QueryWrapper<>();
    	 objectQueryWrapper1.eq("teacher_id", ShiroKit.getUser().getId()).select(new String[] {"class_id"});
    	 List<Object> objs = teacherClassService.getBaseMapper().selectObjs(objectQueryWrapper1);
    	 List<Classes> list = new ArrayList<Classes>();
    	 if(null !=objs && !objs.isEmpty()) {
    		 QueryWrapper<Classes> objectQueryWrapper = new QueryWrapper<>();
        	 objectQueryWrapper.in("class_id", objs);
        	 list = classesService.getBaseMapper().selectList(objectQueryWrapper);
    	 }
    	
    	 
        return ResponseData.success(list);
    }
    
    
    
    /**
     * 新增接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ClassesParam classesParam) {
        boolean result = this.classesService.add(classesParam);
        if(result){
            return ResponseData.success();
        }else{
            return ResponseData.error("此班级已存在");
        }
    }

    /**
     * 编辑接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ClassesParam classesParam) {
        boolean result = this.classesService.update(classesParam);
        if(result){
            return ResponseData.success();
        }else{
            return ResponseData.error("此班级已存在");
        }
    }

    /**
     * 删除接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ClassesParam classesParam) {
        this.classesService.delete(classesParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ClassesParam classesParam) {
        Classes detail = this.classesService.getById(classesParam.getDeptId());
        ClassesResult result = new ClassesResult();
        ToolUtil.copyProperties(detail, result);
        Major major = majorService.getById(detail.getMajorId());
        result.setCollege(major.getCollegeId());
        return ResponseData.success(result);
    }

    /**
     * 查询列表
     *
     * @author demo
     * @Date 2022-04-22
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ClassesParam classesParam) {
        return this.classesService.findPageBySpec(classesParam);
    }

}


