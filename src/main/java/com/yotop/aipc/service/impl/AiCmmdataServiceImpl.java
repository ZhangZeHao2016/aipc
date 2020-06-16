package com.yotop.aipc.service.impl;

import com.yotop.aipc.dao.AiCmmdataMapper;
import com.yotop.aipc.model.AiCmmdata;
import com.yotop.aipc.service.AiCmmdataService;
import com.yotop.aipc.core.AbstractService;
import com.yotop.aipc.core.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by zzh on 2020/06/12.
 */
@Service
@Transactional
public class AiCmmdataServiceImpl extends AbstractService<AiCmmdata> implements AiCmmdataService {
    @Resource
    private AiCmmdataMapper aicmmdataMapper;

    private void validate (AiCmmdata aicmmdata){
        //此处一般需要新增代码，校验一些关乎业务逻辑的参数,校验失败抛出ServiceException异常
        //throw new ServiceException("用户名密码错误");
        if(null == aicmmdata.getcId()){throw new ServiceException("cId不能为空！");}
        if(null == aicmmdata.getcPId()){throw new ServiceException("cPId不能为空！");}
        if(StringUtils.isEmpty(aicmmdata.getcFiguer())){throw new ServiceException("cFiguer不能为空！");}
        if(StringUtils.isEmpty(aicmmdata.getcIsfig())){throw new ServiceException("cIsfig不能为空！");}
        if(StringUtils.isEmpty(aicmmdata.getcItem())){throw new ServiceException("cItem不能为空！");}
        if(StringUtils.isEmpty(aicmmdata.getcPartnumber())){throw new ServiceException("cPartnumber不能为空！");}
        if(null == aicmmdata.getcTier()){throw new ServiceException("cTier不能为空！");}
        if(StringUtils.isEmpty(aicmmdata.getcNomenclature())){throw new ServiceException("cNomenclature不能为空！");}
        if(StringUtils.isEmpty(aicmmdata.getcEddcode())){throw new ServiceException("cEddcode不能为空！");}
        if(StringUtils.isEmpty(aicmmdata.getcUpa())){throw new ServiceException("cUpa不能为空！");}
        if(null == aicmmdata.getcCon()){throw new ServiceException("cCon不能为空！");}
        if(StringUtils.isEmpty(aicmmdata.getcUpdatedate())){throw new ServiceException("cUpdatedate不能为空！");}
        if(StringUtils.isEmpty(aicmmdata.getcUpdateperson())){throw new ServiceException("cUpdateperson不能为空！");}
    }


    @Override
    public List<AiCmmdata> findCmmDataByfig() {
        List<AiCmmdata> aiCmmdata = aicmmdataMapper.selectAll();

        return aiCmmdata;
    }
}