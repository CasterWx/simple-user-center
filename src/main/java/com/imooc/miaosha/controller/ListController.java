package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class ListController {

    @Autowired
    UserService userService;

    @RequestMapping("/to_list")
    public String list(Model model,
                       @CookieValue(value=UserService.COOKIE_NAME_TOKEN, required=false) String cookieToken,
                       @CookieValue(value=UserService.COOKIE_NAME_TOKEN, required=false) String paramToken) {
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return "login";
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        User user = userService.getByToken(token);
        model.addAttribute("user", user);
        return "goods_list";
    }
}
