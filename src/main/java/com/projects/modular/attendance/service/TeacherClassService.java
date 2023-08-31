package com.projects.modular.attendance.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.TeacherClass;
import com.projects.modular.attendance.model.params.TeacherClassParam;
import com.projects.modular.attendance.model.result.TeacherClassResult;

/**
 * <p>
 * 教师管理班级 服务类
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
public interface TeacherClassService extends IService<TeacherClass> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2022-05-11
     */
    void add(TeacherClassParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2022-05-11
     */
    void delete(TeacherClassParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2022-05-11
     */
    void update(TeacherClassParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2022-05-11
     */
    TeacherClassResult findBySpec(TeacherClassParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2022-05-11
     */
    List<TeacherClassResult> findListBySpec(TeacherClassParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2022-05-11
     */
     LayuiPageInfo findPageBySpec(TeacherClassParam param);

}
