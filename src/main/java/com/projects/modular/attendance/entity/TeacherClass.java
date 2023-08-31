package com.projects.modular.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 教师管理班级
 * </p>
 *
 * @author demo
 * @since 2022-05-11
 */
@TableName("t_teacher_class")
public class TeacherClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "teacher_class_id", type = IdType.ID_WORKER)
    private Long teacherClassId;

    /**
     * 教师
     */
    @TableField("teacher_id")
    private Long teacherId;

    /**
     * 班级
     */
    @TableField("class_id")
    private Long classId;


    public Long getTeacherClassId() {
        return teacherClassId;
    }

    public void setTeacherClassId(Long teacherClassId) {
        this.teacherClassId = teacherClassId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "TeacherClass{" +
        "teacherClassId=" + teacherClassId +
        ", teacherId=" + teacherId +
        ", classId=" + classId +
        "}";
    }
}
