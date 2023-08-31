package com.projects.modular.attendance.mapper;

import com.projects.modular.attendance.entity.Attendance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学生考勤 Mapper 接口
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
public interface AttendanceMapper extends BaseMapper<Attendance> {

     List<Map> getAttendanceTjData(@Param("deptId")String deptId,@Param("roomId")String roomId,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
}
