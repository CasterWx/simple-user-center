package com.imooc.miaosha.controller;

import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;
import com.imooc.miaosha.util.ValidatorUtil;
import com.imooc.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@RequestMapping("/to_login")
	public String login() {
		return "login";
	}

	@RequestMapping("/do_login")
	@ResponseBody
	public Result<Boolean> do_login(HttpServletResponse response, LoginVo loginVo) {
		logger.info(loginVo.toString());
		String passInput = loginVo.getPassword();
		String mobile = loginVo.getMobile();
		if (StringUtils.isEmpty(passInput)) {
			return Result.error(CodeMsg.LOGIN_PASSWORD_ERROR);
		}
		if (StringUtils.isEmpty(mobile)) {
			return Result.error(CodeMsg.LOGIN_PHONE_ERROR);
		}
		if (!ValidatorUtil.isMobile(mobile)) {
			return Result.error(CodeMsg.MOBILE_ERROR);
		}
		// 登录
		CodeMsg co = userService.login(response, loginVo);
		if (co.getCode() == 0) {
			return Result.success(true);
		} else {
			return Result.error(co);
		}
	}

}
