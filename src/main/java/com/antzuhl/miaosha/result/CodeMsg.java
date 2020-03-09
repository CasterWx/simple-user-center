package com.antzuhl.miaosha.result;

public class CodeMsg {
	private int code;
	private String msg;
	
	//通用异常
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");

	//登录模块 5002XX
	public static CodeMsg LOGIN_PASSWORD_ERROR = new CodeMsg(500200, "登录密码为空异常");
	public static CodeMsg LOGIN_PHONE_ERROR = new CodeMsg(500201, "登录手机号异常");
	public static CodeMsg MOBILE_ERROR = new CodeMsg(500202, "手机号格式异常");
	public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500203, "手机号不存在");
	public static CodeMsg PASSWORD_ERROR = new CodeMsg(500204, "密码输入错误");

	//注册模块 5003XX
	public static CodeMsg REGISTORY_ERROR = new CodeMsg(500301, "用户注册异常");
	public static CodeMsg REGISTORY_DATA_ERROR = new CodeMsg(500302, "账号注册信息异常");
	public static CodeMsg MOBILE_EXIST = new CodeMsg(500303, "账号已被注册");


	//商品模块 5003XX
	
	//订单模块 5004XX
	
	//秒杀模块 5005XX
	
	
	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}
