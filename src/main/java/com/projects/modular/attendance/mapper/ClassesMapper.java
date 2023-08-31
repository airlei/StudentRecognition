package com.projects.modular.attendance.mapper;

import com.projects.modular.attendance.entity.Classes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projects.modular.attendance.entity.College;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 班级表 Mapper 接口
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
public interface ClassesMapper extends BaseMapper<Classes> {
    /**
     * 通过名字获取班级
     */
    Classes getClassesByNm(@Param("name") String name);
}
