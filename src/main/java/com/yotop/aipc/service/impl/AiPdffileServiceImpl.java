package com.yotop.aipc.service.impl;

import com.yotop.aipc.config.DateUtils;
import com.yotop.aipc.core.Constants;
import com.yotop.aipc.dao.AiCmmdataMapper;
import com.yotop.aipc.dao.AiPdffileMapper;
import com.yotop.aipc.model.AiCmmdata;
import com.yotop.aipc.model.AiPdffile;
import com.yotop.aipc.service.AiPdffileService;
import com.yotop.aipc.core.AbstractService;
import com.yotop.aipc.core.ServiceException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zzh on 2020/06/11.
 */
@Service
@Transactional
public class AiPdffileServiceImpl extends AbstractService<AiPdffile> implements AiPdffileService {

    private final Logger logger = LoggerFactory.getLogger(AiPdffileServiceImpl.class);

    @Resource
    private AiPdffileMapper aipdffileMapper;

//    @Value("${constants.uploadPath}")
//    String uploadPath;

    @Resource
    private Constants constants;

    @Resource
    private AiCmmdataMapper aiCmmdataMapper;


    private void validate (AiPdffile aipdffile){
        //此处一般需要新增代码，校验一些关乎业务逻辑的参数,校验失败抛出ServiceException异常
        //throw new ServiceException("用户名密码错误");
        if(null == aipdffile.getpId()){throw new ServiceException("pId不能为空！");}
        if(StringUtils.isEmpty(aipdffile.getpFilename())){throw new ServiceException("pFilename不能为空！");}
        if(StringUtils.isEmpty(aipdffile.getpRealname())){throw new ServiceException("pRealname不能为空！");}
        if(StringUtils.isEmpty(aipdffile.getpSubmitdate())){throw new ServiceException("pSubmitdate不能为空！");}
        if(StringUtils.isEmpty(aipdffile.getpSubmitperson())){throw new ServiceException("pSubmitperson不能为空！");}
        if(null == aipdffile.getpIschangesuccess()){throw new ServiceException("pIschangesuccess不能为空！");}
    }

    /**
     * 将文件上传到指定的位置
     *
     * @param file
     * @param originalFilename
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public String uploadToPath(MultipartFile file, String originalFilename, String atenname) throws IllegalStateException, IOException {
        String fileName = DateUtils.getThisDateHMSS();
        if (StringUtils.isNotEmpty(originalFilename) && originalFilename.contains(".")) {
            String[] split = originalFilename.split("\\.");
            String s = split[split.length - 1];
            fileName = fileName + "." + s;
        }
        //transferTo是保存文件，参数就是要保存到的目录和名字
        String filePath =constants.getUploadPath() + File.separator + atenname + File.separator;
        File file1 = new File(filePath);
        // 判断要输出的目录是否存在 ，不存在则创建
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        if (!file1.exists() && !file1.isDirectory()) {
            logger.info("目录不存在---------------------" + filePath);
            file1.mkdir();
        } else {
            logger.info("目录存在---------------------" + filePath);
        }
        file.transferTo(new File(filePath + fileName));
        return fileName;
    }
    @Override
    public AiPdffile upload(MultipartFile file) throws Exception {
        //取得当前上传文件的文件名称
        String originalFilename = file.getOriginalFilename();
        String fileName = this.uploadToPath(file, originalFilename, "PDF");

        logger.info("文件类型：" + file.getContentType());
        logger.info("原文件名：" + originalFilename);
        logger.info("是否为空：" + file.isEmpty());
        logger.info("文件大小：" + file.getSize());

        AiPdffile aiPdffile = new AiPdffile();
        aiPdffile.setpFilename(fileName);

        aiPdffile.setpRealname(originalFilename);
        return aiPdffile;
    }

    public static void main(String[] args) throws Exception {

//        String[] args1 = new String[] { "python", "C:\\Users\\wangkai\\Desktop\\商飞\\test\\test\\venv\\Include\\Testpdf1TabulaTest.py", "20200611181156683.pdf" ,"353".toString(),"360".toString()};
//        //Create a Process instance and execute commands
//        Process pr = Runtime.getRuntime().exec(args1);
//        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(), "GBK"));
//        String line = null;
//        while ((line = in.readLine()) != null) {
//            System.out.println(line);
//        }
        String result = "['', 'N', '1', '1707432', '1', 'PANEL ASSEMBLY - FRONT - INSEPARABLE', '', 'RF', '']";

//        Object o = JSONArray.toJSON(result);
//        System.out.println(o);

        // net.sf.jso
        JSONArray array = JSONArray.fromObject(result);
        System.out.println(array);
//        Object[] obj = new Object[array.size()];
//        for (int i = 0; i < array.size(); i++) {
//            JSONObject jsonObject = array.getJSONObject(i);
//            obj[i] = JSONObject.toBean(jsonObject, cl);
//        }

//        String[] strings = new String[21];
//        strings[0] = "212";
//        System.out.println(strings.length);

    }

    @Override
    public void parsingPdf(String filename, String realname, Integer startpage, Integer endpage,String type) throws Exception{
        //判空
        if (StringUtils.isAnyEmpty(filename,realname,type) ){
            throw new ServiceException("参数获取失败！");
        }
        //需要先吧 章节题 存起来
        AiPdffile aiPdffile = new AiPdffile();
        aiPdffile.setpRealname(realname);
        aiPdffile.setpFilename(filename);
        aiPdffile.setpSubmitdate(DateUtils.getToday());
        aipdffileMapper.insert(aiPdffile);


        String[] args1 = new String[]{};
        if ("tabula".equals(type)){
            args1 = new String[] { "python", "C:\\Users\\wangkai\\Desktop\\商飞\\test\\test\\venv\\Include\\Testpdf1TabulaTest.py", filename ,startpage.toString(),endpage.toString()};
        }else if ("camlot".equals(type)){
            args1 = new String[] { "python", "C:\\Users\\wangkai\\Desktop\\商飞\\test\\test\\venv\\Include\\Testpdf1CamelotTest.py", filename ,startpage.toString(),endpage.toString()};
        }

        //Create a Process instance and execute commands
        Process pr = Runtime.getRuntime().exec(args1);
        //Get the result produced by executing the above commands

        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(), "GBK"));
        String line = null;
        String result = "";
        int i = 0 ;
        ArrayList<AiCmmdata> aiCmmdataArrayList = new ArrayList<>();
        String figs = "";

        while ((line = in.readLine()) != null) {
            result = line;
            if (result.contains("开始表格---------------------------")){
                //需要合并表格信息
                figs = result.substring(result.length() - 1, result.length());
                continue;
            }
            JSONArray array = JSONArray.fromObject(result);
            AiCmmdata aiCmmdata = new AiCmmdata();
            aiCmmdata.setcIsfig(array.get(1).toString());
            aiCmmdata.setcItem(array.get(2).toString());
            aiCmmdata.setcPartnumber(array.get(3).toString());
            if (!StringUtils.isEmpty(array.get(4).toString())){
                aiCmmdata.setcTier(Integer.parseInt(array.get(4).toString()));
            }
            aiCmmdata.setcNomenclature(array.get(5).toString());
            aiCmmdata.setcEddcode(array.get(6).toString());
            aiCmmdata.setcUpa(array.get(7).toString());
            if (!StringUtils.isEmpty(array.get(8).toString())){
                aiCmmdata.setcCon(Integer.parseInt(array.get(8).toString()));
            }
            aiCmmdata.setcFiguer(figs);
            aiCmmdata.setcUpdatedate(DateUtils.getToday());
            aiCmmdata.setcPId(aiPdffile.getpId());
            aiCmmdataArrayList.add(aiCmmdata);
        }

        //保存数据
        aiCmmdataMapper.insertList(aiCmmdataArrayList);


    }
}