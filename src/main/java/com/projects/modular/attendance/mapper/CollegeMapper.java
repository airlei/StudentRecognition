package com.projects.modular.attendance.mapper;

import com.projects.modular.attendance.entity.College;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projects.modular.system.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 学院 Mapper 接口
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
public interface CollegeMapper extends BaseMapper<College> {

    /**
     * 通过名字获取学院
     */
    College getCollegeByNm(@Param("name") String name);

}
