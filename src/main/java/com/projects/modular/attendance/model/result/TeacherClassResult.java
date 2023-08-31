package com.projects.modular.attendance.model.result;

import lombok.Data;
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
public class TeacherClassResult implements Serializable {

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
    
    private String classes;

}
