package com.projects.modular.attendance.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 学院
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
@Data
public class CollegeResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long collegeId;

    /**
     * 学院
     */
    private String name;

}
