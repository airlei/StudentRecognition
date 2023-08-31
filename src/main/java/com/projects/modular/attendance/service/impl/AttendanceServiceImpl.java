package com.projects.modular.attendance.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.core.shiro.ShiroKit;
import com.projects.modular.attendance.entity.Attendance;
import com.projects.modular.attendance.mapper.AttendanceMapper;
import com.projects.modular.attendance.model.params.AttendanceParam;
import com.projects.modular.attendance.model.result.AttendanceResult;
import  com.projects.modular.attendance.service.AttendanceService;
import com.projects.modular.system.entity.User;
import com.projects.modular.system.mapper.UserMapper;

import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 学生考勤 服务实现类
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements AttendanceService {
	@Autowired
	private UserMapper  userMapper;
	@Autowired
    private AttendanceMapper attendanceMapper;
    @Override
    public void add(AttendanceParam param){
        Attendance entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(AttendanceParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(AttendanceParam param){
        Attendance oldEntity = getOldEntity(param);
        Attendance newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public AttendanceResult findBySpec(AttendanceParam param){
        return null;
    }

    @Override
    public List<AttendanceResult> findListBySpec(AttendanceParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(AttendanceParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Attendance> objectQueryWrapper = new QueryWrapper<>();
      
        User user2 = userMapper.selectById(ShiroKit.getUser().getId());
        if(!ShiroKit.isAdmin()) {
        
        	  if("1323546363237978113".equals(user2.getRoleId())) {
        			objectQueryWrapper.eq("user_id", ShiroKit.getUser().getId());
              }
        	 
              
        }
        
        if(ToolUtil.isNotEmpty(param.getDeptId())) {
        	objectQueryWrapper.eq("class_id", param.getDeptId());
        }
        
        if(ToolUtil.isNotEmpty(param.getUserId())) {
        	objectQueryWrapper.eq("user_id", param.getUserId());
        }
        if(ToolUtil.isNotEmpty(param.getTimes())) {
        	objectQueryWrapper.like("time", param.getTimes());
        }
        IPage page = this.page(pageContext, objectQueryWrapper);
        List<Attendance> records = page.getRecords();
        List<AttendanceResult>  list =new ArrayList<>();
        for (Attendance attendance : records) {
        	AttendanceResult result = new AttendanceResult();
        	ToolUtil.copyProperties(attendance, result);
        	User user = userMapper.selectById(attendance.getUserId());
        	if(null != user) {
        		result.setName(user.getName());
        		result.setNo(user.getEmail());
        	}
        	list.add(result);
		}
        page.setRecords(list);
        return LayuiPageFactory.createPageInfo(page);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
	public LayuiPageInfo findPageBySpec1(AttendanceParam param) {
        Page pageContext = getPageContext();
        QueryWrapper<Attendance> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("room_id", param.getRoomId());
        User user2 = userMapper.selectById(ShiroKit.getUser().getId());
        if(!ShiroKit.isAdmin()) {
        
        	  if("1323546363237978113".equals(user2.getRoleId())) {
        			objectQueryWrapper.eq("user_id", ShiroKit.getUser().getId());
              }
        	 
              
        }
        
       
        
        if(ToolUtil.isNotEmpty(param.getUserId())) {
        	objectQueryWrapper.eq("user_id", param.getUserId());
        }
        objectQueryWrapper.orderByDesc("time");
        IPage page = this.page(pageContext, objectQueryWrapper);
        List<Attendance> records = page.getRecords();
        List<AttendanceResult>  list =new ArrayList<>();
        for (Attendance attendance : records) {
        	AttendanceResult result = new AttendanceResult();
        	ToolUtil.copyProperties(attendance, result);
        	User user = userMapper.selectById(attendance.getUserId());
        	if(null != user) {
        		result.setName(user.getName());
        		result.setNo(user.getEmail());
        	}
        	list.add(result);
		}
        page.setRecords(list);
        return LayuiPageFactory.createPageInfo(page);
	}

    @Override
    public List<Map> getAttendanceTjData(String deptId, String roomId, String beginTime, String endTime) {
        return attendanceMapper.getAttendanceTjData(deptId,roomId,beginTime,endTime);
    }

    private Serializable getKey(AttendanceParam param){
        return param.getAttendId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Attendance getOldEntity(AttendanceParam param) {
        return this.getById(getKey(param));
    }

    private Attendance getEntity(AttendanceParam param) {
        Attendance entity = new Attendance();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
