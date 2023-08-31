package com.projects.modular.attendance.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.Student;
import com.projects.modular.attendance.model.params.StudentParam;
import com.projects.modular.attendance.model.result.StudentResult;

/**
 * <p>
 * 学生管理 服务类
 * </p>
 *
 * @author demo
 * @since 2022-05-12
 */
public interface StudentService extends IService<Student> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2022-05-12
     */
    void add(StudentParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2022-05-12
     */
    void delete(StudentParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2022-05-12
     */
    void update(StudentParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2022-05-12
     */
    StudentResult findBySpec(StudentParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2022-05-12
     */
    List<StudentResult> findListBySpec(StudentParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2022-05-12
     */
     LayuiPageInfo findPageBySpec(StudentParam param);

}
