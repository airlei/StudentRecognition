package com.projects.modular.attendance.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.Major;
import com.projects.modular.attendance.model.params.MajorParam;
import com.projects.modular.attendance.model.result.MajorResult;

/**
 * <p>
 * 专业 服务类
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
public interface MajorService extends IService<Major> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2022-05-11
     */
    void add(MajorParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2022-05-11
     */
    void delete(MajorParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2022-05-11
     */
    void update(MajorParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2022-05-11
     */
    MajorResult findBySpec(MajorParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2022-05-11
     */
    List<MajorResult> findListBySpec(MajorParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2022-05-11
     */
     LayuiPageInfo findPageBySpec(MajorParam param);

}
