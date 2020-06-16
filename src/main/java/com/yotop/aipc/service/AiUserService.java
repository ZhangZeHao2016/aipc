package com.yotop.aipc.service;
import com.yotop.aipc.model.AiUser;
import com.yotop.aipc.core.Service;


/**
 * Created by zzh on 2020/05/22.
 */
public interface AiUserService extends Service<AiUser> {

    AiUser check(String username, String password);

    AiUser test();

}
