/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.projects.core.common.exception;

import cn.stylefeng.roses.kernel.model.exception.AbstractBaseExceptionEnum;

/**
 * @author fengshuonan
 * @Description 所有业务异常的枚举
 * @date 2016年11月12日 下午5:04:51
 */
public enum BizExceptionEnum implements AbstractBaseExceptionEnum {

	/**
	 * 字典
	 */
	DICT_EXISTED(400, "字典已经存在"), ERROR_CREATE_DICT(500, "创建字典失败"), ERROR_WRAPPER_FIELD(500, "包装字典属性失败"),
	ERROR_CODE_EMPTY(500, "字典类型不能为空"),

	/**
	 * 文件上传
	 */
	FILE_READING_ERROR(400, "FILE_READING_ERROR!"), FILE_NOT_FOUND(400, "FILE_NOT_FOUND!"), UPLOAD_ERROR(500, "上传图片出错"),

	/**
	 * 权限和数据问题
	 */
	DB_RESOURCE_NULL(400, "数据库中没有该资源"), NO_PERMITION(405, "权限异常"), REQUEST_INVALIDATE(400, "请求数据格式不正确"),
	INVALID_KAPTCHA(400, "验证码不正确"), CANT_DELETE_ADMIN(600, "不能删除超级管理员"), CANT_FREEZE_ADMIN(600, "不能冻结超级管理员"),
	CANT_CHANGE_ADMIN(600, "不能修改超级管理员角色"),

	/**
	 * 账户问题
	 */
	NOT_LOGIN(401, "当前用户未登录"), USER_ALREADY_REG(401, "该用户已经注册"), NO_THIS_USER(400, "没有此用户"),
	EXIST_EMAIL(401, "邮箱已经被注册"), EXIST_PHONE(401, "手机号已经被注册"), USER_NOT_EXISTED(400, "没有此用户"),
	ACCOUNT_FREEZED(401, "此账号已冻结，请与客服联系4008728271"), OLD_PWD_NOT_RIGHT(402, "原密码不正确"),
	TWO_PWD_NOT_MATCH(405, "两次输入密码不一致"), NO_ACCOUNT(406, "账号不能为空"), ACCOUNT_LOGINED(407, "账号已经在另一个地方登录"),
	EXIST_COLLNO(401, "学号已经存在"), EXIST_ACCOUNT(401, "账号已经存在"),
	EXIST_COLLEGE(401, "此学院已经存在"),EXIST_MAJOR(401, "此专业已经存在"),
	EXIST_CLASSES(401, "此班级已经存在"),

	/**
	 * 错误的请求
	 */
	MENU_PCODE_COINCIDENCE(400, "菜单编号和副编号不能一致"), EXISTED_THE_MENU(400, "菜单编号重复，不能添加"),
	DICT_MUST_BE_NUMBER(400, "字典的值必须为数字"), REQUEST_NULL(400, "请求有错误"), SESSION_TIMEOUT(400, "会话超时"),
	SERVER_ERROR(500, "服务器异常"),

	/**
	 * token异常
	 */
	TOKEN_EXPIRED(700, "token过期"), TOKEN_ERROR(700, "token验证失败"),

	AUDIT_CODE(2001, "此账号未经审核，或审核未通过，请与客服联系： 4008728271"),
	/**
	 * 短信
	 */
	MSG_CODE_NO(800, "短信验证码为空"),
	/**
	 * 新闻
	 */
	NEWS_CODE_NO(1090, "未找到该新闻"),
	/**
	 * 签名异常
	 */
	/**
	 * 课堂
	 */

	SIGN_ERROR(700, "签名验证失败"), CLASSROOM_NOT_EXIST_ERROR(1001, "课堂不存在,或不是管理者"),
	/**
	 * 其他
	 */
	EXIST_THINKTANK_ERROR(500,"已经存在相同的智库"),
	EXIST_THINKTANK_WORK_ERROR(500,"已经存在相同的词条"),
	EXIST_THINKTANK_TYPE_ERROR(500,"已经存在相同的词条类型"),
	/**
	 * 其他
	 */
	AUTH_REQUEST_ERROR(400, "账号密码错误");


	
	BizExceptionEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private Integer code;

	private String message;

	@Override
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
