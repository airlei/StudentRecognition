package com.projects.modular.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.Classes;
import com.projects.modular.attendance.entity.College;
import com.projects.modular.attendance.model.params.CollegeParam;
import com.projects.modular.attendance.service.CollegeService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;


/**
 * 学院控制器
 *
 * @author demo
 * @Date 2022-05-11 23:51:00
 */
@Controller
@RequestMapping("/college")
public class CollegeController extends BaseController {

    private String PREFIX = "/modular/college";

    @Autowired
    private CollegeService collegeService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/college.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/college_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/college_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(CollegeParam collegeParam) {
        this.collegeService.add(collegeParam);
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
    public ResponseData editItem(CollegeParam collegeParam) {
        this.collegeService.update(collegeParam);
        return ResponseData.success();
    }

    
    @RequestMapping("/getList")  
    @ResponseBody
    public ResponseData getList() {
    	 QueryWrapper<College> objectQueryWrapper = new QueryWrapper<>();
    	
    	 List<College> list = collegeService.getBaseMapper().selectList(objectQueryWrapper);
    	 
        return ResponseData.success(list);
    }
    
    /**
     * 删除接口
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(CollegeParam collegeParam) {
        this.collegeService.delete(collegeParam);
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
    public ResponseData detail(CollegeParam collegeParam) {
        College detail = this.collegeService.getById(collegeParam.getCollegeId());
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
    public LayuiPageInfo list(CollegeParam collegeParam) {
        return this.collegeService.findPageBySpec(collegeParam);
    }

}


