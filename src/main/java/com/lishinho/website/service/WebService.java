package com.lishinho.website.service;

import org.springframework.stereotype.Service;

//IoC思想
@Service
public class WebService {
    public String getMsg(int UserId){
        return "hello msg " + UserId;
    }
}
