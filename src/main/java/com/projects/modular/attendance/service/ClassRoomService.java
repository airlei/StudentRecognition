package com.projects.modular.attendance.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.ClassRoom;
import com.projects.modular.attendance.model.params.ClassRoomParam;
import com.projects.modular.attendance.model.result.ClassRoomResult;

/**
 * <p>
 * 课堂 服务类
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
public interface ClassRoomService extends IService<ClassRoom> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2022-04-22
     */
    void add(ClassRoomParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2022-04-22
     */
    void delete(ClassRoomParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2022-04-22
     */
    void update(ClassRoomParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
    ClassRoomResult findBySpec(ClassRoomParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
    List<ClassRoomResult> findListBySpec(ClassRoomParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
     LayuiPageInfo findPageBySpec(ClassRoomParam param);

}
