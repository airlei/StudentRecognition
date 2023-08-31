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
import com.projects.modular.attendance.entity.Classes;
import com.projects.modular.attendance.entity.TeacherClass;
import com.projects.modular.attendance.mapper.ClassesMapper;
import com.projects.modular.attendance.mapper.TeacherClassMapper;
import com.projects.modular.attendance.model.params.TeacherClassParam;
import com.projects.modular.attendance.model.result.TeacherClassResult;
import  com.projects.modular.attendance.service.TeacherClassService;

import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 教师管理班级 服务实现类
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
@Service
public class TeacherClassServiceImpl extends ServiceImpl<TeacherClassMapper, TeacherClass> implements TeacherClassService {
	@Autowired
	private ClassesMapper classesMapper;
    @Override
    public void add(TeacherClassParam param){
        TeacherClass entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(TeacherClassParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(TeacherClassParam param){
        TeacherClass oldEntity = getOldEntity(param);
        TeacherClass newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public TeacherClassResult findBySpec(TeacherClassParam param){
        return null;
    }

    @Override
    public List<TeacherClassResult> findListBySpec(TeacherClassParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(TeacherClassParam param){
        Page pageContext = getPageContext();
        QueryWrapper<TeacherClass> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("teacher_id", param.getTeacherId());
        IPage page = this.page(pageContext, objectQueryWrapper);
        List<TeacherClass> records = page.getRecords();
        List<TeacherClassResult> list = new ArrayList<TeacherClassResult>();
        for (TeacherClass teacherClass : records) {
        	TeacherClassResult result = new TeacherClassResult();
        	ToolUtil.copyProperties(teacherClass, result);
        	Classes classes = classesMapper.selectById(teacherClass.getClassId());
        	if(null !=classes) {
        		result.setClasses(classes.getName());
        	}
        	list.add(result);
		}
        page.setRecords(list);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(TeacherClassParam param){
        return param.getTeacherClassId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private TeacherClass getOldEntity(TeacherClassParam param) {
        return this.getById(getKey(param));
    }

    private TeacherClass getEntity(TeacherClassParam param) {
        TeacherClass entity = new TeacherClass();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
