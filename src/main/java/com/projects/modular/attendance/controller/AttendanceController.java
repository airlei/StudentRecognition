package com.projects.modular.attendance.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.projects.config.properties.GunsProperties;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.core.shiro.ShiroKit;
import com.projects.core.util.FaceSpot;
import com.projects.modular.attendance.entity.Attendance;
import com.projects.modular.attendance.entity.ClassRoom;
import com.projects.modular.attendance.model.params.AttendanceParam;
import com.projects.modular.attendance.model.result.Result;
import com.projects.modular.attendance.service.AttendanceService;
import com.projects.modular.attendance.service.ClassRoomService;
import com.projects.modular.system.entity.User;
import com.projects.modular.system.service.UserService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import sun.misc.BASE64Decoder;

/**
 * 学生考勤控制器
 *
 * @author demo
 * @Date 2022-04-22 15:53:22
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceController extends BaseController {

    private String PREFIX = "/modular/attendance";

    @Autowired
    private AttendanceService attendanceService;
	@Autowired
	private GunsProperties gunsProperties;

	@Autowired
	private UserService userService;
	@Autowired
	private ClassRoomService  classRoomService;
	
    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/attendance.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/add")
    public String add(Model model,Long roomId) {
    	model.addAttribute("roomId", roomId);
        return PREFIX + "/attendance_add.html";
    }

    
    
    /**
     * 
     * @param model
     * @param roomId
     * @return
     */
    @RequestMapping("/map")
    public String map(Model model,Long attendId) {
    	
    	Attendance attendance = attendanceService.getById(attendId);
    	model.addAttribute("lat", attendance.getLat());
    	model.addAttribute("lng", attendance.getLng());
        return PREFIX + "/map.html";
    }
    
    
    
    
    /**
     * 图标统计
  * @param model
  * @return
  */
 @RequestMapping("/statistics")
 public String statistics(Model model) {
 	  List<String>  zhouOrder = new ArrayList<>();
 	  QueryWrapper<Attendance> objectQueryWrapper = new QueryWrapper<>();
 	   objectQueryWrapper.ge("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 00:00:00");
 	   objectQueryWrapper.le("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 23:59:59");
	   List<Attendance> list = attendanceService.getBaseMapper().selectList(objectQueryWrapper);
	
	   model.addAttribute("A1", list.size());
	 
	   QueryWrapper<Attendance> objectQueryWrapper1 = new QueryWrapper<>();
	   objectQueryWrapper1.ge("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 00:00:00");
	   objectQueryWrapper1.le("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 23:59:59");
	   objectQueryWrapper1.eq("type", "迟到");
	   List<Attendance> list2 = attendanceService.getBaseMapper().selectList(objectQueryWrapper1);	
	   model.addAttribute("A2", list2.size());
	   
	   
	   
	   
	   QueryWrapper<Attendance> objectQueryWrapper13 = new QueryWrapper<>();
	  
	   objectQueryWrapper13.eq("type", "迟到");
	   List<Attendance> list2111 = attendanceService.getBaseMapper().selectList(objectQueryWrapper13);
	   
	   zhouOrder.add(list2111.size()+"");
	   
	   QueryWrapper<Attendance> objectQueryWrapper2 = new QueryWrapper<>();
	   objectQueryWrapper2.ge("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 00:00:00");
	   objectQueryWrapper2.le("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 23:59:59");
	   objectQueryWrapper2.eq("type", "正常签到");
	   List<Attendance> list3= attendanceService.getBaseMapper().selectList(objectQueryWrapper2);	
	   model.addAttribute("A3", list3.size());
	   
	   QueryWrapper<Attendance> objectQueryWrapper21111 = new QueryWrapper<>();
	  
	   objectQueryWrapper21111.eq("type", "正常签到");
	   List<Attendance> list31111= attendanceService.getBaseMapper().selectList(objectQueryWrapper21111);	
	   
	  
	   
	   
	   QueryWrapper<Attendance> objectQueryWrapper3 = new QueryWrapper<>();
	   objectQueryWrapper3.ge("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 00:00:00");
	   objectQueryWrapper3.le("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 23:59:59");
	   objectQueryWrapper3.like("type", "假");
	   List<Attendance> list4= attendanceService.getBaseMapper().selectList(objectQueryWrapper3);	
	   model.addAttribute("A4", list4.size());
	  
	   
	   
	   QueryWrapper<Attendance> objectQueryWrapper3222 = new QueryWrapper<>();
	  
	   objectQueryWrapper3222.like("type", "假");
	   List<Attendance> list4222= attendanceService.getBaseMapper().selectList(objectQueryWrapper3);	
	   zhouOrder.add((list4222.size())+"");
	   
	   zhouOrder.add(list31111.size()+"");
	  
	   
	   
	   List<String>  days = new ArrayList<>();
	   List<String>  days1 = new ArrayList<>();
	   List<String>  priceList = new ArrayList<>();
	   List<String>  profitList = new ArrayList<>();
	   for (int i = 6; i >= 1; i--) {
		   Calendar c = Calendar.getInstance();
    	   c.setTime(new Date());
           c.add(Calendar.DATE, - i);
           Date d = c.getTime();
           String day = new SimpleDateFormat("yyyy-MM-dd").format(d);
           days.add(day);
           days1.add(new SimpleDateFormat("MM-dd").format(d));
		 }
	   days.add(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	   days1.add(new SimpleDateFormat("MM-dd").format(new Date()));
	  
	   List<String>  zhouOrder1 = new ArrayList<>();
	   for (String string : days) {
		
		   QueryWrapper<Attendance> objectQueryWrapper21 = new QueryWrapper<>();
		   objectQueryWrapper21.ge("time", string+" 00:00:00");
		   objectQueryWrapper21.le("time", string+" 23:59:59");
		   objectQueryWrapper21.eq("type", "迟到");
		   List<Attendance> list31= attendanceService.getBaseMapper().selectList(objectQueryWrapper21);	
    	  
    	   priceList.add(list31.size()+"");
    	   
    	   QueryWrapper<Attendance> objectQueryWrapper22= new QueryWrapper<>();
    	   objectQueryWrapper22.ge("time", string+" 00:00:00");
    	   objectQueryWrapper22.le("time", string+" 23:59:59");
     	  objectQueryWrapper22.like("type", "假");
   	      List<Attendance> list22 = attendanceService.getBaseMapper().selectList(objectQueryWrapper22);
     	   
    
    	   
    	   
		   profitList.add(list22.size()+"");
		 
		   	   
		}
	   


	   model.addAttribute("days", days1);
	   model.addAttribute("priceList", priceList);
	   //model.addAttribute("priceList", Joiner.on(",").join(priceList));
	   model.addAttribute("profitList", profitList);
	   
	   
	   
	 model.addAttribute("yearOrderList", zhouOrder);
  	

	 
	 
	 
  
	   String[] moth = new String[] {"2022-01","2022-02","2022-03","2022-04","2022-05","2022-06","2022-07","2022-08","2022-09","2022-10","2022-11","2022-12"};
	   List<String>  yearOrder = new ArrayList<>();
	  
	   for (int i = 0; i < moth.length; i++) {
		   QueryWrapper<Attendance> objectQueryWrapper10 = new QueryWrapper<>();
		   objectQueryWrapper10.apply("DATE_FORMAT(time,'%Y-%m') = '"+moth[i]+"'");
		   objectQueryWrapper10.eq("type", "迟到");
		   List<Attendance> list12 = attendanceService.getBaseMapper().selectList(objectQueryWrapper10);		   
		   yearOrder.add(list12.size()+"");
	  
	   }
	 
	 
	 
	   model.addAttribute("moth", moth);
	   model.addAttribute("yearOrderprice", yearOrder);
	   
	 
	 
	 
 	
     return PREFIX + "/statistics.html";
 }


	@RequestMapping("/statistics1")
	@ResponseBody
	public ResponseData statistics1(String deptId,String roomId,String timeLimit) {
		String beginTime="";
		String endTime="";
		if(!StringUtils.isEmpty(timeLimit)){
 	   	   String[] times = timeLimit.split("-");
		   beginTime=times[0].trim();
		   endTime=times[1].trim();
		}
		try{
			List<Map> list= attendanceService.getAttendanceTjData(deptId,roomId,beginTime,endTime);
			return ResponseData.success(list);
		}catch (Exception e){
			return ResponseData.error("考勤数据异常");
		}
	}





	@RequestMapping("/getAttendance")
	 public String getAttendance() {
	     return PREFIX + "/attendance2.html";
	 }
    
    
    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/attendance_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(AttendanceParam attendanceParam) {
        this.attendanceService.add(attendanceParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(AttendanceParam attendanceParam) {
        this.attendanceService.update(attendanceParam);
        return ResponseData.success();
    }
    
    
    /**
     * 人脸识别打卡
     * @return
     */
    @RequestMapping("/searchFace")
    @ResponseBody
    public ResponseData searchFace(String img,Long roomId,String lng,String lat) {
    	JSONObject js = FaceSpot.searchFace(img, "face", ShiroKit.getUser().getId()+"");
		System.out.println(js.toString(2));
		com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(js.toString());
		Result data = com.alibaba.fastjson.JSONObject.toJavaObject(jsonObject, Result.class);
		if(null == data.getResult()) {
			//result.put("t", false);
			return ResponseData.error("未能识别");
		}else {
			 String fileSavePath = "";
	    	String serverSavePath = "";
	    	String pictureName = UUID.randomUUID().toString() + ".jpeg" ;
	    	//验证人脸返回相似度
			double score = data.getResult().getUser_list().get(0).getScore();
			if(score>80) {
				//result.put("t", true);
				
				   BASE64Decoder decoder = new BASE64Decoder();  
			        try   
			        {  
			            //Base64解码  
			            byte[] b = decoder.decodeBuffer(img);  
			          //  System.out.println("解码完成");
			            for(int i=0;i<b.length;++i)  
			            {  
			                if(b[i]<0)  
			                {//调整异常数据  
			                    b[i]+=256;  
			                }  
			            }
			           
			    		
			           // System.out.println("开始生成图片");
			            //生成jpeg图片
						File directory = new File("src/main/webapp/static/images");
						String paths = directory.getCanonicalPath();

			            fileSavePath = gunsProperties.getFileUploadPath();
						String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/images/";
						serverSavePath = gunsProperties.getServerUploadPath();
			            //String savepath = request.getServletContext().getRealPath(picpath);
			            OutputStream out = new FileOutputStream(path + pictureName);
			            out.write(b);  
			            out.flush();  
			            out.close();

						OutputStream out1 = new FileOutputStream(paths+'/' + pictureName);
						out1.write(b);
						out1.flush();
						out1.close();

					} catch (Exception e) {
						e.printStackTrace();
						
					}
			        
//			        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        QueryWrapper<Attendance> objectQueryWrapper = new QueryWrapper<>();
			     
			        objectQueryWrapper.eq("room_id", roomId);
			        objectQueryWrapper.eq("user_id", ShiroKit.getUser().getId());
			        Integer count = attendanceService.getBaseMapper().selectCount(objectQueryWrapper);
			        if(count>0) {
			        	return ResponseData.error("该课堂已打卡~");
			        }
			        
			        ClassRoom classRoom = classRoomService.getById(roomId);			        			        
			        AttendanceParam  param  =new AttendanceParam();
			        param.setTime(new Date());
			        param.setLat(lat);
			        param.setLng(lng);
			        param.setUserId(ShiroKit.getUser().getId());
			        param.setPic(serverSavePath + pictureName);
			        if(new Date().before(classRoom.getStartTime())) {
			        	return ResponseData.error("别着急~还未到打卡时间");
			        }else
			        if(new Date().after(classRoom.getStartTime())&&new Date().before(classRoom.getEndTime())) {
						  param.setType("正常签到");
					}else {
						 param.setType("迟到");
					}
			       
			        User user = userService.getById(ShiroKit.getUser().getId());
			        param.setDeptId(user.getDeptId());
			        param.setRoomId(roomId);
			        attendanceService.add(param);
			        
			}else {
				return ResponseData.error("未能识别，人脸和账号本人不匹配，换个姿势再试试吧~");
			}
        
    }
		   return ResponseData.success();
		
    }
    
    

    /**
     * 删除接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(AttendanceParam attendanceParam) {
        this.attendanceService.delete(attendanceParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(AttendanceParam attendanceParam) {
        Attendance detail = this.attendanceService.getById(attendanceParam.getAttendId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author demo
     * @Date 2022-04-22
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(AttendanceParam attendanceParam) {
        return this.attendanceService.findPageBySpec(attendanceParam);
    }

    
    
    @ResponseBody
    @RequestMapping("/list1")
    public LayuiPageInfo list1(AttendanceParam attendanceParam) {
        return this.attendanceService.findPageBySpec1(attendanceParam);
    }
    
}


