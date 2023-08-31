package com.projects.modular.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 课堂
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
@TableName("t_class_room")
public class ClassRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "room_id", type = IdType.ID_WORKER)
    private Long roomId;

    /**
     * 课堂名称
     */
    @TableField("name")
    private String name;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 老师
     */
    @TableField("user_id")
    private String userId;

    /**
     * 班级
     */
    @TableField("classes_id")
    private Long classesId;

    /**
     * 签到要求
     */
    @TableField("demand")
    private String demand;


    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getClassesId() {
        return classesId;
    }

    public void setClassesId(Long classesId) {
        this.classesId = classesId;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
        "roomId=" + roomId +
        ", name=" + name +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", userId=" + userId +
        ", classesId=" + classesId +
        ", demand=" + demand +
        "}";
    }
}
