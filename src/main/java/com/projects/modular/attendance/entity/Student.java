package com.projects.modular.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 学生管理
 * </p>
 *
 * @author demo
 * @since 2022-05-12
 */
@TableName("t_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "student_id", type = IdType.ID_WORKER)
    private Long studentId;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 学号
     */
    @TableField("user_name")
    private String userName;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 联系电话
     */
    @TableField("tel")
    private String tel;

    /**
     * 学院
     */
    @TableField("college")
    private String college;

    /**
     * 专业
     */
    @TableField("major")
    private String major;

    /**
     * 班级
     */
    @TableField("classes_id")
    private Long classesId;


    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Long getClassesId() {
        return classesId;
    }

    public void setClassesId(Long classesId) {
        this.classesId = classesId;
    }

    @Override
    public String toString() {
        return "Student{" +
        "studentId=" + studentId +
        ", name=" + name +
        ", userName=" + userName +
        ", password=" + password +
        ", tel=" + tel +
        ", college=" + college +
        ", major=" + major +
        ", classesId=" + classesId +
        "}";
    }
}
