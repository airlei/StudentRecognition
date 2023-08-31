package com.projects.modular.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 专业
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
@TableName("t_major")
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "major_id", type = IdType.ID_WORKER)
    private Long majorId;

    /**
     * 专业名称
     */
    @TableField("name")
    private String name;

    /**
     * 学院
     */
    @TableField("college_id")
    private Long collegeId;


    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    @Override
    public String toString() {
        return "Major{" +
        "majorId=" + majorId +
        ", name=" + name +
        ", collegeId=" + collegeId +
        "}";
    }
}
