package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.UserDao;
import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.util.MD5Util;
import com.imooc.miaosha.util.UUIDUtil;
import com.imooc.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    UserDao userDao;
    @Autowired
    RedisService redisService;

    public User getById(Long id) {
        return userDao.getById(id);
    }

    public CodeMsg registory(User user) {
        // 查询账号是否被注册
        User sel = getById(user.getId());
        if (sel!=null) {
            return CodeMsg.MOBILE_EXIST;
        }
        // 如果未注册，创建用户
        // 首先加密密码
        user.setPassword(MD5Util.formPassToDBPass(user.getPassword(), user.getSalt()));
        int ret = userDao.save(user);
        if (ret == 1) {
            return CodeMsg.SUCCESS;
        } else {
            return CodeMsg.REGISTORY_ERROR;
        }
    }

    public CodeMsg login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        User user = getById(Long.parseLong(mobile));
        if (user == null) {
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        //验证密码
        String dbPass = user.getPassword();
        String slat = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, slat);
        if (!calcPass.equals(dbPass)) {
            return CodeMsg.PASSWORD_ERROR;
        }
        String token = UUIDUtil.uuid();
        redisService.set(token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(100000);
        cookie.setPath("/");
        response.addCookie(cookie);
        return CodeMsg.SUCCESS;
    }

    public User getByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return redisService.get(token, User.class);
    }
}
