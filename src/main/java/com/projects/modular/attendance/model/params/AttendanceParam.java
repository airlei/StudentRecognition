package com.projects.modular.attendance.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 学生考勤
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
@Data
public class AttendanceParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long attendId;

    /**
     * 考勤学生
     */
    private Long userId;

    /**
     * 考勤时间
     */
    private Date time;

    /**
     * 考勤类型
     */
    private String type;
    
    private String pic;
    
    private String times;
    private Long deptId;
    private Long roomId;
    private String lng;
    private String lat;
    
    @Override
    public String checkParam() {
        return null;
    }

}
