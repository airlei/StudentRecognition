package com.projects.modular.attendance.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.Attendance;
import com.projects.modular.attendance.model.params.AttendanceParam;
import com.projects.modular.attendance.model.result.AttendanceResult;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 学生考勤 服务类
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
public interface AttendanceService extends IService<Attendance> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2022-04-22
     */
    void add(AttendanceParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2022-04-22
     */
    void delete(AttendanceParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2022-04-22
     */
    void update(AttendanceParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
    AttendanceResult findBySpec(AttendanceParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
    List<AttendanceResult> findListBySpec(AttendanceParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
     LayuiPageInfo findPageBySpec(AttendanceParam param);
     LayuiPageInfo findPageBySpec1(AttendanceParam param);

    List<Map> getAttendanceTjData(String deptId, String roomId,String beginTime,String endTime);

}
