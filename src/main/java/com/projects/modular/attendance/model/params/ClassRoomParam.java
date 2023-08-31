package com.projects.modular.attendance.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 课堂
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
@Data
public class ClassRoomParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long roomId;

    /**
     * 课堂名称
     */
    private String name;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 老师
     */
    private String userId;

    /**
     * 班级
     */
    private Long classesId;

    /**
     * 签到要求
     */
    private String demand;

    @Override
    public String checkParam() {
        return null;
    }

}
