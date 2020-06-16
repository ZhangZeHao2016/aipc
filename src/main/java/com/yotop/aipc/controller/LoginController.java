package com.yotop.aipc.controller;

import com.yotop.aipc.config.JwtUtil;
import com.yotop.aipc.config.MD5Util;
import com.yotop.aipc.core.Result;
import com.yotop.aipc.core.ResultGenerator;
import com.yotop.aipc.model.AiUser;
import com.yotop.aipc.service.AiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2020/5/21.
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Value("${token.key}")
    String tokenKey;

    @Autowired
    private AiUserService aiUserService;

    @RequestMapping("/login")
    public Result login(String username, String password,HttpServletRequest request){

        password = MD5Util.string2MD5(password);
        System.out.println(password);
        AiUser user = aiUserService.check(username,password);
        String token = "";
        if (user==null){
            return ResultGenerator.genFailResult("用户不存在");
        }
        if (user.getuPassword().equals(password)){
                String salt = request.getHeader("X-forwarded-for");
                Map<String,Object> map = new HashMap<>();
                map.put("username",username);
                token = JwtUtil.encode(tokenKey, map, salt);

            if (token!=null){
                return ResultGenerator.genSuccessResult(token);
            }else {
                return ResultGenerator.genFailResult("验证失败");
            }
        }
        return ResultGenerator.genFailResult("密码错误");

    }

}
