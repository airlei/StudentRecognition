package com.projects.modular.attendance.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class CollegeParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long collegeId;

    /**
     * 学院
     */
    private String name;

    @Override
    public String checkParam() {
        return null;
    }

}
