package com.projects.modular.attendance.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.Classes;
import com.projects.modular.attendance.model.params.ClassesParam;
import com.projects.modular.attendance.model.result.ClassesResult;

/**
 * <p>
 * 班级表 服务类
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
public interface ClassesService extends IService<Classes> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2022-04-22
     */
    boolean add(ClassesParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2022-04-22
     */
    void delete(ClassesParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2022-04-22
     */
    boolean update(ClassesParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
    ClassesResult findBySpec(ClassesParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
    List<ClassesResult> findListBySpec(ClassesParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
     LayuiPageInfo findPageBySpec(ClassesParam param);

}
