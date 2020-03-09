package com.antzuhl.miaosha.vo;

import com.antzuhl.miaosha.domain.User;
import com.antzuhl.miaosha.util.MD5Util;
import com.antzuhl.miaosha.util.ValidatorUtil;

public class LogonVo {

    private Long mobile;
    private String email;
    private String nickname;
    private String password;


    public boolean isValid() {
        // 校验， email需要校验，还有长度校验
        return mobile!=null && ValidatorUtil.isMobile(String.valueOf(mobile))
                && email!=null && nickname!=null && password!=null;
    }

    public boolean isNotValid() {
        return !isValid();
    }

    public User toData() {
        User user = new User();
        user.setId(getMobile());
        user.setNickname(getNickname());
        user.setPassword(getPassword());
        user.setEmail(getEmail());
        user.setSalt(MD5Util.phoneToSalt(String.valueOf(getMobile()), getPassword()));
        return user;
    }

    @Override
    public String toString() {
        return "LogonVo{" +
                "mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
