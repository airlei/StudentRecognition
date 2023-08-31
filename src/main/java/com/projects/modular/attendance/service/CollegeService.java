package com.projects.modular.attendance.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.College;
import com.projects.modular.attendance.model.params.CollegeParam;
import com.projects.modular.attendance.model.result.CollegeResult;

/**
 * <p>
 * 学院 服务类
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
public interface CollegeService extends IService<College> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2022-05-11
     */
    void add(CollegeParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2022-05-11
     */
    void delete(CollegeParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2022-05-11
     */
    void update(CollegeParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2022-05-11
     */
    CollegeResult findBySpec(CollegeParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2022-05-11
     */
    List<CollegeResult> findListBySpec(CollegeParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2022-05-11
     */
     LayuiPageInfo findPageBySpec(CollegeParam param);

}
