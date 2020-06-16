package com.yotop.aipc.service;
import com.yotop.aipc.model.AiPdffile;
import com.yotop.aipc.core.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by zzh on 2020/06/11.
 */
public interface AiPdffileService extends Service<AiPdffile> {

    AiPdffile upload(MultipartFile file) throws Exception;

    void parsingPdf(String filename, String realname, Integer startpage, Integer endpage,String type) throws Exception;
}
