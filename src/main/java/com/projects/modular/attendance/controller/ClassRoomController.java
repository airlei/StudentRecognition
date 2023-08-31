package com.projects.modular.attendance.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.projects.modular.attendance.entity.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.core.shiro.ShiroKit;
import com.projects.modular.attendance.entity.ClassRoom;
import com.projects.modular.attendance.model.params.ClassRoomParam;
import com.projects.modular.attendance.service.ClassRoomService;
import com.projects.modular.system.entity.User;
import com.projects.modular.system.service.UserService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;

import java.util.List;


/**
 * 课堂控制器
 *
 * @author demo
 * @Date 2022-04-22 10:43:38
 */
@Controller
@RequestMapping("/classRoom")
public class ClassRoomController extends BaseController {

    private String PREFIX = "/modular/college/classRoom";

    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private UserService userService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/classRoom.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/classRoom_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/classRoom_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ClassRoomParam classRoomParam) {
    	User user = userService.getById(ShiroKit.getUser().getId());
    	classRoomParam.setUserId(ShiroKit.getUser().getId().toString());
    
        this.classRoomService.add(classRoomParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ClassRoomParam classRoomParam) {
        this.classRoomService.update(classRoomParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ClassRoomParam classRoomParam) {
        this.classRoomService.delete(classRoomParam);
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
    public ResponseData detail(ClassRoomParam classRoomParam) {
        ClassRoom detail = this.classRoomService.getById(classRoomParam.getRoomId());
        return ResponseData.success(detail);
    }
    @RequestMapping("/getList")
    @ResponseBody
    public ResponseData getList(Long classesId) {
        QueryWrapper<ClassRoom> objectQueryWrapper = new QueryWrapper<>();
        if(classesId!=null){
            objectQueryWrapper.eq("classes_id", classesId);
        }
        List<ClassRoom> list = classRoomService.getBaseMapper().selectList(objectQueryWrapper);
        return ResponseData.success(list);
    }
    /**
     * 查询列表
     *
     * @author demo
     * @Date 2022-04-22
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ClassRoomParam classRoomParam) {
        return this.classRoomService.findPageBySpec(classRoomParam);
    }

}


