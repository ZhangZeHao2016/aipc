package com.yotop.aipc.dao;

import com.yotop.aipc.core.Mapper;
import com.yotop.aipc.model.AiUser;

public interface AiUserMapper extends Mapper<AiUser> {
    AiUser selectTest();
}