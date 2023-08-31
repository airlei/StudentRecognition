package com.projects.modular.attendance.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.core.shiro.ShiroKit;
import com.projects.modular.attendance.entity.ClassRoom;
import com.projects.modular.attendance.entity.Classes;
import com.projects.modular.attendance.mapper.ClassRoomMapper;
import com.projects.modular.attendance.mapper.ClassesMapper;
import com.projects.modular.attendance.model.params.ClassRoomParam;
import com.projects.modular.attendance.model.result.ClassRoomResult;
import  com.projects.modular.attendance.service.ClassRoomService;
import com.projects.modular.system.entity.User;
import com.projects.modular.system.mapper.UserMapper;

import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 课堂 服务实现类
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
@Service
public class ClassRoomServiceImpl extends ServiceImpl<ClassRoomMapper, ClassRoom> implements ClassRoomService {
	@Autowired
	private ClassesMapper classesMapper;
	@Autowired
	private UserMapper userMapper;
    @Override
    public void add(ClassRoomParam param){
        ClassRoom entity = getEntity(param);
        this.save(entity);
    }
 
    @Override
    public void delete(ClassRoomParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ClassRoomParam param){
        ClassRoom oldEntity = getOldEntity(param);
        ClassRoom newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ClassRoomResult findBySpec(ClassRoomParam param){
        return null;
    }

    @Override
    public List<ClassRoomResult> findListBySpec(ClassRoomParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ClassRoomParam param){
        Page pageContext = getPageContext();
        QueryWrapper<ClassRoom> objectQueryWrapper = new QueryWrapper<>();
        if (ToolUtil.isNotEmpty(param.getName())){
        	objectQueryWrapper.like("name", param.getName());
        }
        
        
        User user2 = userMapper.selectById(ShiroKit.getUser().getId());
        if("1359012972354043905".equals(user2.getRoleId())) {
        	objectQueryWrapper.eq("user_id", user2.getUserId());
        }else if("1323546363237978113".equals(user2.getRoleId())) {//学生角色
        	
        	objectQueryWrapper.eq("classes_id", user2.getDeptId());
        }
        
        objectQueryWrapper.orderByDesc("start_time");
        IPage page = this.page(pageContext, objectQueryWrapper);
        List<ClassRoom> records = page.getRecords();//查询所有的课堂，可以查询到课堂名称和老师
        List<ClassRoomResult> list = new ArrayList<>();
        for (ClassRoom classRoom : records) {
        	 ClassRoomResult result = new ClassRoomResult();
        	 ToolUtil.copyProperties(classRoom, result);
        	  Classes classes = classesMapper.selectById(classRoom.getClassesId());
        	  if(null != classes) {
        		  result.setClasses(classes.getName());
        	 }
        	  User user = userMapper.selectById(classRoom.getUserId());
        	  if(null != user) {
        		  result.setUserId(user.getName());
        	  }
        	  list.add(result);
		}
        page.setRecords(list);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ClassRoomParam param){
        return param.getRoomId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ClassRoom getOldEntity(ClassRoomParam param) {
        return this.getById(getKey(param));
    }

    private ClassRoom getEntity(ClassRoomParam param) {
        ClassRoom entity = new ClassRoom();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
