
package com.projects.modular.system.controller;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.projects.config.properties.GunsProperties;
import com.projects.core.common.constant.DefaultAvatar;
import com.projects.core.common.constant.factory.ConstantFactory;
import com.projects.core.common.exception.BizExceptionEnum;
import com.projects.core.shiro.ShiroKit;
import com.projects.core.shiro.ShiroUser;
import com.projects.modular.attendance.entity.News;
import com.projects.modular.attendance.service.NewsService;
import com.projects.modular.system.entity.User;
import com.projects.modular.system.factory.UserFactory;
import com.projects.modular.system.service.UserService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.exception.enums.CoreExceptionEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用控制器

 */
@Controller
@RequestMapping("/system")
@Slf4j
public class SystemController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private NewsService newsService;
	

	@Autowired
	private GunsProperties gunsProperties;

	/**
	 * 控制台页面
	
	 */
	@RequestMapping("/console")
	public String console(Model model) {
		 QueryWrapper<News> objectQueryWrapper = new QueryWrapper<>();
			
		   List<News> selectList = newsService.getBaseMapper().selectList(objectQueryWrapper);
		   for (News news : selectList) {
			   news.setContent(news.getContent().replace("& lt;", "<")
						.replace("& gt;", ">"));
				
		}
		   model.addAttribute("list", selectList);
		return "/modular/frame/console.html";
	}




	/**
	 * 主页面

	 */
	@RequestMapping("/welcome")
	public String welcome() {
		return "/modular/frame/welcome.html";
	}

	/**
	 * 主题页面
	
	 */
	@RequestMapping("/theme")
	public String theme() {
		return "/modular/frame/theme.html";
	}

	/**
	 * 跳转到修改密码界面
	
	 */
	@RequestMapping("/user_chpwd")
	public String chPwd() {
		return "/modular/frame/password.html";
	}

	/**
	 * 个人消息列表

	 */
	@RequestMapping("/message")
	public String message() {
		return "/modular/frame/message.html";
	}

	/**
	 * 跳转到查看用户详情页面

	 */
	@RequestMapping("/user_info")
	public String userInfo(Model model) {
		Long userId = ShiroKit.getUserNotNull().getId();
		User user = this.userService.getById(userId);

		model.addAllAttributes(BeanUtil.beanToMap(user));
		model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
	
		
		return "/modular/frame/user_info.html";
	}

	/**
	 * 通用的树列表选择器

	 */
	@RequestMapping("/commonTree")
	public String deptTreeList(@RequestParam("formName") String formName, @RequestParam("formId") String formId,
			@RequestParam("treeUrl") String treeUrl, Model model) {

		if (ToolUtil.isOneEmpty(formName, formId, treeUrl)) {
			throw new RequestEmptyException("请求数据不完整！");
		}

		try {
			model.addAttribute("formName", URLDecoder.decode(formName, "UTF-8"));
			model.addAttribute("formId", URLDecoder.decode(formId, "UTF-8"));
			model.addAttribute("treeUrl", URLDecoder.decode(treeUrl, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RequestEmptyException("请求数据不完整！");
		}

		return "/common/tree_dlg.html";
	}

	/**
	 * 上传头像
	
	 */
	@RequestMapping("/uploadAvatar")
	@ResponseBody
	public Object uploadAvatar(@RequestParam String avatar) {

		if (ToolUtil.isEmpty(avatar)) {
			throw new RequestEmptyException("请求头像为空");
		}

		avatar = avatar.substring(avatar.indexOf(",") + 1);

		

		return SUCCESS_TIP;
	}

	/**
	 * 预览头像

	 */
	@RequestMapping("/previewAvatar")
	@ResponseBody
	public Object previewAvatar(HttpServletResponse response) {

		ShiroUser currentUser = ShiroKit.getUser();
		if (currentUser == null) {
			throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
		}

		// 获取当前用户的头像id
		User user = userService.getById(currentUser.getId());
		String avatar = user.getAvatar();

		// 如果头像id为空就返回默认的
		if (ToolUtil.isEmpty(avatar)) {
			avatar = DefaultAvatar.BASE_64_AVATAR;
		} else {
			
		}

		// 输出图片的文件流
		try {
			response.setContentType("image/jpeg");
			byte[] decode = Base64.decode(avatar);
			response.getOutputStream().write(decode);
		} catch (IOException e) {
			log.error("获取图片的流错误！", avatar);
			throw new ServiceException(CoreExceptionEnum.SERVICE_ERROR);
		}

		return null;
	}

	/**
	 * 获取当前用户详情

	 */
	@RequestMapping("/currentUserInfo")
	@ResponseBody
	public ResponseData getUserInfo() {

		ShiroUser currentUser = ShiroKit.getUser();
		if (currentUser == null) {
			throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
		}

		User user = userService.getById(currentUser.getId());
		Map<String, Object> map = UserFactory.removeUnSafeFields(user);

		HashMap<Object, Object> hashMap = CollectionUtil.newHashMap();
		hashMap.putAll(map);
		hashMap.put("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
	

		return ResponseData.success(hashMap);
	}

	/**
	 * layui上传组件 通用文件上传接口

	 */
	@RequestMapping(method = RequestMethod.POST, path = "/upload")
	@ResponseBody
	public ResponseData layuiUpload(@RequestPart("file") MultipartFile picture) {
		String fileSavePath = "";
		String serverSavePath = "";
		String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
		try {
			//获取项目classes/static的地址
			String staticPath = new ClassPathResource("static/images").getFile().getPath();

			// 图片存储目录及图片名称
			String url_path = File.separator + pictureName;
			//图片保存路径
			String savePath = staticPath + File.separator + url_path;
			System.out.println("图片保存地址："+savePath);

			//获取项目路径
			File directory = new File("src/main/webapp/static/images");
			String paths = directory.getCanonicalPath();
			File dest = new File(paths+'/' + pictureName);
			System.out.println(dest.getAbsoluteFile());

			fileSavePath = gunsProperties.getFileUploadPath();
			serverSavePath = gunsProperties.getServerUploadPath();

			String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/images/";
			FileInputStream fileInputStream = (FileInputStream) picture.getInputStream();
			//以流的方式上传
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + pictureName));
			byte[] bs = new byte[1024];
			int len;
			while ((len = fileInputStream.read(bs)) != -1) {
				bos.write(bs, 0, len);
			}
			bos.flush();
			bos.close();

			//picture.transferTo(new File(fileSavePath + pictureName));
			picture.transferTo(dest);
		} catch (Exception e) {
			throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
		}

		HashMap<String, Object> map = new HashMap<>();
		map.put("fileId", IdWorker.getIdStr());
		map.put("filePath", fileSavePath + pictureName);
		map.put("serverPath", serverSavePath + pictureName);
		return ResponseData.success(0, "上传成功", map);
	}

	public static void main(String[] args) throws IOException {
		String newPath = new ClassPathResource("static/images").getFile().getPath();
		//String staticPath = this.getClass().getClassLoader().getResource("static").getFile();

		//System.out.println("图片保存地址："+staticPath);

	}
}
