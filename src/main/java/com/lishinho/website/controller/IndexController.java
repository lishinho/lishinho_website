package com.lishinho.website.controller;

import com.lishinho.website.aspect.LogAspect;
import com.lishinho.website.model.User;
import com.lishinho.website.service.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    WebService webService;

    @RequestMapping(path = {"/", "/index"})
    @ResponseBody
    public String index(HttpSession httpSession){
        return "Hello worldXD " + httpSession.getAttribute("msg") + webService.getMsg(2);
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
        logger.info("visit ftl");
        model.addAttribute("value","Agoda");//String
        List<String> colors = Arrays.asList(new String[]{"Red","blue","Green","Yellow"});
        model.addAttribute("colors",colors);//list
        HashMap<String, String> map = new HashMap<>();
        for(int i = 0; i < 4; i++){
            map.put(String.valueOf(i),String.valueOf(i*i));
        }
        model.addAttribute("map", map);//map
        model.addAttribute("User", new User("Lishinho"));//class
        return "home";
    }

    @RequestMapping(path = {"/req"}, method = {RequestMethod.GET})
    @ResponseBody
    public String request(Model model1, HttpServletResponse response,
    HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        Enumeration<String> headerName = request.getHeaderNames();
        while(headerName.hasMoreElements()){
            String name = headerName.nextElement();
            sb.append(name + ":" + request.getHeader(name)+"<br>");
        }
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                sb.append("Cookies: " + cookie.getName() + " Values:" + cookie.getValue());
            }
        }
        sb.append(request.getCookies() + "<br>");
        sb.append(request.getMethod() + "<br>");
        sb.append(request.getRequestURI() + "<br>");
        response.addHeader("Hello","world");
        response.addCookie(new Cookie("NewUser","lishinho"));
        return sb.toString();
    }

    @RequestMapping(path = {"/redirect/{code}"}, method = {RequestMethod.GET})
    public RedirectView redirect(@PathVariable("code") int code, HttpSession httpSession){
        httpSession.setAttribute("msg", "jump from redirect");
        RedirectView red = new RedirectView("/",true);
        if(code == 301){
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        return red;
    }

    @RequestMapping(path={"/admin"})
    @ResponseBody
    public String admin(@RequestParam("key") String key){
        if("admin".equals(key)){
            return "hello admin";
        }
        throw new IllegalArgumentException("Wrong number");
    }

    @ExceptionHandler()
    @ResponseBody
    public String error(Exception e){
        return "error:" + e.getMessage();
    }
}
