package com.antzuhl.miaosha.controller;

import com.antzuhl.miaosha.result.CodeMsg;
import com.antzuhl.miaosha.result.Result;
import com.antzuhl.miaosha.service.UserService;
import com.antzuhl.miaosha.vo.LogonVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/logon")
public class RegistorController {

    private static Logger logger = LoggerFactory.getLogger(RegistorController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String logonPage() {
        return "logon";
    }

    @RequestMapping("/do_logon")
    @ResponseBody
    public Result<CodeMsg> doLogon(LogonVo logonVo) {
        logger.info(logonVo.toString());
        if (logonVo.isNotValid()) {
            return Result.error(CodeMsg.REGISTORY_DATA_ERROR);
        }
        CodeMsg result = userService.registory(logonVo.toData());
        if (result.getCode() == 0) {
            return Result.success(result);
        } else {
            return Result.error(result);
        }
    }
}
