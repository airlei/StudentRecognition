package com.projects.modular.attendance.mapper;

import com.projects.modular.attendance.entity.College;
import com.projects.modular.attendance.entity.Major;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 专业 Mapper 接口
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
public interface MajorMapper extends BaseMapper<Major> {
    /**
     * 通过名字获取专业
     */
    Major getMajorByNm(@Param("name") String name);
}
