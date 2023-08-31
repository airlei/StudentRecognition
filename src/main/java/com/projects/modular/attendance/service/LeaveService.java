package com.projects.modular.attendance.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.Leave;
import com.projects.modular.attendance.model.params.LeaveParam;
import com.projects.modular.attendance.model.result.LeaveResult;

/**
 * <p>
 * 请假管理 服务类
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
public interface LeaveService extends IService<Leave> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2022-04-22
     */
    void add(LeaveParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2022-04-22
     */
    void delete(LeaveParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2022-04-22
     */
    void update(LeaveParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
    LeaveResult findBySpec(LeaveParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
    List<LeaveResult> findListBySpec(LeaveParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
     LayuiPageInfo findPageBySpec(LeaveParam param);

}
