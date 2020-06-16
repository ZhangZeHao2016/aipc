package com.yotop.aipc.service.impl;

import com.yotop.aipc.dao.AiUserMapper;
import com.yotop.aipc.model.AiUser;
import com.yotop.aipc.service.AiUserService;
import com.yotop.aipc.core.AbstractService;
import com.yotop.aipc.core.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.util.List;


/**
 * Created by zzh on 2020/05/22.
 */
@Service
@Transactional
public class AiUserServiceImpl extends AbstractService<AiUser> implements AiUserService {
    @Resource
    private AiUserMapper aiuserMapper;

    private void validate (AiUser aiuser){
        //此处一般需要新增代码，校验一些关乎业务逻辑的参数,校验失败抛出ServiceException异常
        //throw new ServiceException("用户名密码错误");
        if(null == aiuser.getuId()){throw new ServiceException("uId不能为空！");}
        if(StringUtils.isEmpty(aiuser.getuName())){throw new ServiceException("uName不能为空！");}
        if(StringUtils.isEmpty(aiuser.getuPassword())){throw new ServiceException("uPassword不能为空！");}
    }

    @Override
    public AiUser check(String username, String password) {
        AiUser aiUser = new AiUser();
        aiUser.setuName(username);
//        aiUser.setuPassword(password);
        AiUser user = aiuserMapper.selectOne(aiUser);
        if (user!=null){
            return user;
        }
        return null;
    }

    @Override
    public AiUser test() {
        AiUser aiUser = aiuserMapper.selectTest();
        return  aiUser;
    }
}