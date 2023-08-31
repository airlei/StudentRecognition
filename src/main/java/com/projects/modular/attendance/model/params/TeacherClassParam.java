package com.projects.modular.attendance.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 教师管理班级
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
@Data
public class TeacherClassParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long teacherClassId;

    /**
     * 教师
     */
    private Long teacherId;

    /**
     * 班级
     */
    private Long classId;

    @Override
    public String checkParam() {
        return null;
    }

}
