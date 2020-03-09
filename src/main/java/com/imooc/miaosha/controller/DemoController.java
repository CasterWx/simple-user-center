package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@RequestMapping("/")
	@ResponseBody
	public String home() {
	        return "Hello World!";
	}

	//1.rest api json输出 2.页面
	@RequestMapping("/hello")
	@ResponseBody
	public Result<String> hello() {
		return Result.success("hello,imooc");
		// return new Result(0, "success", "hello,imooc");
	}
	 	
	@RequestMapping("/helloError")
	@ResponseBody
	public Result<String> helloError() {
		return Result.error(CodeMsg.SERVER_ERROR);
		//return new Result(500102, "XXX");
	}
	 	
	@RequestMapping("/thymeleaf")
	public String thymeleaf(Model model) {
		model.addAttribute("name", "Joshua");
		return "hello";
	}

	@RequestMapping("/to_login")
	public String login() {
		return "login";
	}

	@RequestMapping("/do_login")
	@ResponseBody
	public Result<Boolean> do_login() {
		return null;
	}

	@Autowired
	RedisService redisService;

	@RequestMapping("/redisget")
	@ResponseBody
	public String getRedis() {
		return redisService.get("key1", User.class).toString();
	}

	@RequestMapping("/redisset")
	@ResponseBody
	public Boolean setRedis() {
		User user = new User();
		user.setNickname("wxh");
		user.setPassword("123");
		return redisService.set("key1", user);
	}
}
