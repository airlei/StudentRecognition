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
import com.projects.modular.attendance.entity.College;
import com.projects.modular.attendance.entity.Major;
import com.projects.modular.attendance.entity.Student;
import com.projects.modular.attendance.mapper.MajorMapper;
import com.projects.modular.attendance.mapper.StudentMapper;
import com.projects.modular.attendance.model.params.StudentParam;
import com.projects.modular.attendance.model.result.StudentResult;
import com.projects.modular.attendance.service.ClassesService;
import com.projects.modular.attendance.service.CollegeService;
import  com.projects.modular.attendance.service.StudentService;

import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 学生管理 服务实现类
 * </p>
 *
 * @author demo
 * @since 2022-05-12
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private MajorMapper majorMapper;
	@Autowired
	private ClassesService classesService;
    @Override
    public void add(StudentParam param){
        Student entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(StudentParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(StudentParam param){
        Student oldEntity = getOldEntity(param);
        Student newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public StudentResult findBySpec(StudentParam param){
        return null;
    }

    @Override
    public List<StudentResult> findListBySpec(StudentParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(StudentParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Student> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        List<Student> records = page.getRecords();
        List<StudentResult>  list = new ArrayList<StudentResult>();
        for (Student student : records) {
        	StudentResult result = new StudentResult();
        	ToolUtil.copyProperties(student, result);
        	College college = collegeService.getById(student.getCollege());
        	if(null != college) {
        		result.setCollege(student.getName());
        	}
        	Major major = majorMapper.selectById(student.getMajor());
        	if(null != major) {
        		result.setMajor(major.getName());
        	}
        	Classes classes = classesService.getById(student.getClassesId());
        	if(null != classes) {
        		result.setClasses(classes.getName());
        	}
        	list.add(result);
		}
        page.setRecords(list);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(StudentParam param){
        return param.getStudentId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Student getOldEntity(StudentParam param) {
        return this.getById(getKey(param));
    }

    private Student getEntity(StudentParam param) {
        Student entity = new Student();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
