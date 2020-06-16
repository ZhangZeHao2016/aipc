package com.yotop.aipc.service;
import com.yotop.aipc.model.AiCmmdata;
import com.yotop.aipc.core.Service;

import java.util.List;


/**
 * Created by zzh on 2020/06/12.
 */
public interface AiCmmdataService extends Service<AiCmmdata> {

    List<AiCmmdata> findCmmDataByfig();


}
