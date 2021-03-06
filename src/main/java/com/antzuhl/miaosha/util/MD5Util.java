package com.antzuhl.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass, String salt) {
        String formPass = inputPassFormPass(inputPass);
        return formPassToDBPass(formPass, salt);
    }

    public static String phoneToSalt(String mobile, String password) {
        return md5(md5(mobile) + md5(password)).substring(0,8);
    }

    public static void main(String[] args) {
        System.out.println(inputPassFormPass("wxh1325200471"));
        System.out.println(formPassToDBPass(inputPassFormPass("wxh1325200471"), "7f8146c9"));
        System.out.println(inputPassToDBPass("wxh1325200471","7f8146c9"));
    }
}
