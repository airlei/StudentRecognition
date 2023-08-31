package com.projects.modular.attendance.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 学生管理
 * </p>
 *
 * @author demo
 * @since 2022-05-12
 */
@Data
public class StudentParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long studentId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 学号
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 学院
     */
    private String college;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级
     */
    private Long classesId;

    @Override
    public String checkParam() {
        return null;
    }

}
