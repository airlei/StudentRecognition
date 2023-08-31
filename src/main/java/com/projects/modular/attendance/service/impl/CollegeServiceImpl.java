package com.projects.modular.attendance.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.projects.core.common.exception.BizExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.College;
import com.projects.modular.attendance.mapper.CollegeMapper;
import com.projects.modular.attendance.model.params.CollegeParam;
import com.projects.modular.attendance.model.result.CollegeResult;
import  com.projects.modular.attendance.service.CollegeService;

import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 学院 服务实现类
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;
    @Override
    public void add(CollegeParam param){
        College college = collegeMapper.getCollegeByNm(param.getName());
        if(college!=null){
            throw new ServiceException(BizExceptionEnum.EXIST_COLLEGE);
        }

        College entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(CollegeParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(CollegeParam param){
        College college = collegeMapper.getCollegeByNm(param.getName());
        College oldEntity = getOldEntity(param);
        if(college!=null&&!param.getName().equals(oldEntity.getName())){
            throw new ServiceException(BizExceptionEnum.EXIST_COLLEGE);
        }
        College newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CollegeResult findBySpec(CollegeParam param){
        return null;
    }

    @Override
    public List<CollegeResult> findListBySpec(CollegeParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(CollegeParam param){
        Page pageContext = getPageContext();
        QueryWrapper<College> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(CollegeParam param){
        return param.getCollegeId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private College getOldEntity(CollegeParam param) {
        return this.getById(getKey(param));
    }

    private College getEntity(CollegeParam param) {
        College entity = new College();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
