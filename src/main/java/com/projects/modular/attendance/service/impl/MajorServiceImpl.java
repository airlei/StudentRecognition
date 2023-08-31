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
import com.projects.modular.attendance.entity.Major;
import com.projects.modular.attendance.mapper.MajorMapper;
import com.projects.modular.attendance.model.params.MajorParam;
import com.projects.modular.attendance.model.result.MajorResult;
import  com.projects.modular.attendance.service.MajorService;

import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 专业 服务实现类
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {

    @Autowired
    private MajorMapper majorMapper;
    @Override
    public void add(MajorParam param){
        Major major = majorMapper.getMajorByNm(param.getName());
        if(major!=null){
            throw new ServiceException(BizExceptionEnum.EXIST_MAJOR);
        }
        Major entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(MajorParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(MajorParam param){
        Major major = majorMapper.getMajorByNm(param.getName());
        Major oldEntity = getOldEntity(param);
        if(major!=null&&!param.getName().equals(oldEntity.getName())){
            throw new ServiceException(BizExceptionEnum.EXIST_MAJOR);
        }
        Major newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public MajorResult findBySpec(MajorParam param){
        return null;
    }

    @Override
    public List<MajorResult> findListBySpec(MajorParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(MajorParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Major> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("college_id", param.getCollegeId());
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(MajorParam param){
        return param.getMajorId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Major getOldEntity(MajorParam param) {
        return this.getById(getKey(param));
    }

    private Major getEntity(MajorParam param) {
        Major entity = new Major();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
