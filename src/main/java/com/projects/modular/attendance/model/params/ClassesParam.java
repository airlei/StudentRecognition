package com.projects.modular.attendance.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class ClassesParam implements Serializable, BaseValidatingParam {

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
    @Override
    public String checkParam() {
        return null;
    }

}
