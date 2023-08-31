package com.projects.modular.attendance.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 专业
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
@Data
public class MajorResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long majorId;

    /**
     * 专业名称
     */
    private String name;

    /**
     * 学院
     */
    private Long collegeId;

}
