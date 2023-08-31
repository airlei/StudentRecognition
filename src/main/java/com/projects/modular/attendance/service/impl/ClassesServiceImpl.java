package com.projects.modular.attendance.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.projects.core.common.exception.BizExceptionEnum;
import com.projects.modular.attendance.entity.College;
import com.projects.modular.attendance.mapper.CollegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.Classes;
import com.projects.modular.attendance.mapper.ClassesMapper;
import com.projects.modular.attendance.model.params.ClassesParam;
import com.projects.modular.attendance.model.result.ClassesResult;
import  com.projects.modular.attendance.service.ClassesService;

import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 班级表 服务实现类
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {
    @Autowired
    private ClassesMapper classesMapper;
    @Override
    public boolean add(ClassesParam param){
        Classes classes = classesMapper.getClassesByNm(param.getName());
        if(classes!=null){
            return false;
        }
        Classes entity = getEntity(param);
        this.save(entity);
        return true;
    }

    @Override
    public void delete(ClassesParam param){
        this.removeById(getKey(param));
    }

    @Override
    public boolean update(ClassesParam param){
        Classes classes = classesMapper.getClassesByNm(param.getName());
        Classes oldEntity = getOldEntity(param);
        if(classes!=null&&!param.getName().equals(oldEntity.getName())){
            return false;
        }
        Classes newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
        return true;
    }

    @Override
    public ClassesResult findBySpec(ClassesParam param){
        return null;
    }

    @Override
    public List<ClassesResult> findListBySpec(ClassesParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ClassesParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Classes> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ClassesParam param){
        return param.getDeptId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Classes getOldEntity(ClassesParam param) {
        return this.getById(getKey(param));
    }

    private Classes getEntity(ClassesParam param) {
        Classes entity = new Classes();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
