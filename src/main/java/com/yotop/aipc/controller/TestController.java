package com.yotop.aipc.controller;

import com.yotop.aipc.model.AiUser;
import com.yotop.aipc.service.AiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Enzo Cotter on 2020/6/11.
 */
@RestController
public class TestController {

    @Autowired
    private AiUserService aiUserService;

    @RequestMapping("test")
    public String test(){
        return "111";
    }

    @RequestMapping("test22")
    public AiUser test22(){
        AiUser aiUser= aiUserService.test();
        return aiUser;
    }
}
