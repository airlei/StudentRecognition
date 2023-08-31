package com.projects.modular.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 班级表
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
@TableName("t_classes")
public class Classes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级id
     */
    @TableId(value = "class_id", type = IdType.ID_WORKER)
    private Long deptId;

    /**
     * 班级名称
     */
    @TableField("name")
    private String name;

    /**
     * 班级备注
     */
    @TableField("mark")
    private String mark;

    @TableField("major_id")
    private Long majorId;
    
    
    
    
    
    public Long getMajorId() {
		return majorId;
	}

	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}

	public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Dept{" +
        "deptId=" + deptId +
        ", name=" + name +
        ", mark=" + mark +
        "}";
    }
}
