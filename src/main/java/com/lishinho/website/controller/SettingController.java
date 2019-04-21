package com.lishinho.website.controller;

import com.lishinho.website.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class SettingController {
    //IoC思想
    @Autowired
    WebService webService;

    @RequestMapping(path={"/setting"})
    @ResponseBody
    public String admin(HttpSession httpSession){
        return "Setting Ok" + webService.getMsg(1);
    }

}
