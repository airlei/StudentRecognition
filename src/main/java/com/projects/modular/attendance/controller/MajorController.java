package com.projects.modular.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.College;
import com.projects.modular.attendance.entity.Major;
import com.projects.modular.attendance.model.params.MajorParam;
import com.projects.modular.attendance.model.result.MajorResult;
import com.projects.modular.attendance.service.MajorService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;


/**
 * 专业控制器
 *
 * @author demo
 * @Date 2022-05-11 23:51:00
 */
@Controller
@RequestMapping("/major")
public class MajorController extends BaseController {

    private String PREFIX = "/modular/major";

    @Autowired
    private MajorService majorService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/major.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/add")
    public String add(Model model,Long collegeId) {
    	model.addAttribute("collegeId", collegeId);
        return PREFIX + "/major_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/major_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(MajorParam majorParam) {
        this.majorService.add(majorParam);
        return ResponseData.success();
    }

    
    
    @RequestMapping("/getList")  
    @ResponseBody
    public ResponseData getList(Long collegeId) {
    	 QueryWrapper<Major> objectQueryWrapper = new QueryWrapper<>();
    	 objectQueryWrapper.eq("college_id", collegeId);
    	
    	 List<Major> list = majorService.getBaseMapper().selectList(objectQueryWrapper);
    	 
        return ResponseData.success(list);
    }
    
    /**
     * 编辑接口
     *
     * @author demo
     * @Date 2022-05-11
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(MajorParam majorParam) {
        this.majorService.update(majorParam);
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
    public ResponseData delete(MajorParam majorParam) {
        this.majorService.delete(majorParam);
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
    public ResponseData detail(MajorParam majorParam) {
        Major detail = this.majorService.getById(majorParam.getMajorId());
        
      
        
        		
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
    public LayuiPageInfo list(MajorParam majorParam) {
        return this.majorService.findPageBySpec(majorParam);
    }

}


