package com.projects.modular.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 学院
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
@TableName("t_college")
public class College implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "college_id", type = IdType.ID_WORKER)
    private Long collegeId;

    /**
     * 学院
     */
    @TableField("name")
    private String name;


    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "College{" +
        "collegeId=" + collegeId +
        ", name=" + name +
        "}";
    }
}
