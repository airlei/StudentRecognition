package com.projects.modular.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 学生考勤
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
@TableName("t_attendance")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "attend_id", type = IdType.ID_WORKER)
    private Long attendId;

    /**
     * 考勤学生
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 考勤时间
     */
    @TableField("time")
    private Date time;

    /**
     * 考勤类型
     */
    @TableField("type")
    private String type;
    @TableField("pic")
    private String pic;
    @TableField("class_id")
    private Long deptId;
    
    @TableField("room_id")
    private Long roomId;
    @TableField("lng")
    private String lng;
    @TableField("lat")
    private String lat;
    
    public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Attendance{" +
        "attendId=" + attendId +
        ", userId=" + userId +
        ", time=" + time +
        ", type=" + type +
        "}";
    }
}
