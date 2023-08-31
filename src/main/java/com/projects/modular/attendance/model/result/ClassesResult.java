package com.projects.modular.attendance.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 班级表
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
@Data
public class ClassesResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 班级id
     */
    private Long deptId;

    /**
     * 班级名称
     */
    private String name;

    /**
     * 班级备注
     */
    private String mark;
    private Long majorId;
    
    private Long college;
}
