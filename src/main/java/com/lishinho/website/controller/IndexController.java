package com.lishinho.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    @RequestMapping(path = {"/", "/index"})
    @ResponseBody
    public String index(){
        return "Hello worldXD";
    }

    @RequestMapping(path = {"/profile/{group}/{userId}"}, method = {RequestMethod.GET})
    @ResponseBody
    public String profile(@PathVariable("userId") int userId,
                          @PathVariable("group") String group){
        return String.format("Profile page of %s - %d", group,userId);
    }

    @RequestMapping(path = {"/profile/{group}"})
    @ResponseBody
    public String profile(@PathVariable("group") String group,
                            @RequestParam(value = "page", defaultValue = "1", required = false) int page){
        return String.format("Profile page of %s on %d", group, page);
    }

    @RequestMapping(path = {"/ftl"})
    public String template(Model model){
        model.addAttribute("value","Agoda");
        List<String> colors = Arrays.asList(new String[]{"Red","blue","Green","Yellow"});
        model.addAttribute("colors",colors);
        return "home";
    }
}
