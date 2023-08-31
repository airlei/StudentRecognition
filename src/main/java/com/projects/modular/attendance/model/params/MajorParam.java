package com.projects.modular.attendance.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class MajorParam implements Serializable, BaseValidatingParam {

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

    @Override
    public String checkParam() {
        return null;
    }

}
