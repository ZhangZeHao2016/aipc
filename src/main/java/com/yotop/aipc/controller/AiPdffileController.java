package com.yotop.aipc.controller;

import com.yotop.aipc.core.Result;
import com.yotop.aipc.core.ResultGenerator;
import com.yotop.aipc.model.AiPdffile;
import com.yotop.aipc.service.AiPdffileService;
import com.yotop.aipc.core.Pager;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
* Created by zzh on 2020/06/11.
*/
@RestController
@RequestMapping("/ai/pdffile")
public class AiPdffileController {
    @Resource
    private AiPdffileService aiPdffileService;

    @PostMapping
    public Result add(@RequestBody AiPdffile aiPdffile) {
        aiPdffileService.save(aiPdffile);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        aiPdffileService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody AiPdffile aiPdffile) {
        aiPdffileService.update(aiPdffile);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        AiPdffile aiPdffile = aiPdffileService.findById(id);
        return ResultGenerator.genSuccessResult(aiPdffile);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<AiPdffile> list = aiPdffileService.findAll();
        Pager pager = new Pager(list);
        return ResultGenerator.genSuccessResult(pager);
    }

    /**
     * 单文件上传
     *
     * @param file
     * @return
     * @throws IllegalStateException
     * @throws IOException           String
     */
    @PostMapping("/upload")
    //上传的文件会转换成MultipartFile对象，file名字对应html中上传控件的name
    public Result upload(MultipartFile file) throws Exception {
        AiPdffile aiPdffile = aiPdffileService.upload(file);
        return ResultGenerator.genSuccessResult(aiPdffile);
    }



    /**
    * @Author: wx
    * @Description:
    * @DateTime: 2020/6/12 10:33
    * @Params: [filename, realname, startpage, endpage]
    * @Return com.yotop.aipc.core.Result
    */
    @GetMapping("/parsingPdf")
    public Result parsingPdf(String filename,String realname,Integer startpage,Integer endpage,String type) throws Exception{
        aiPdffileService.parsingPdf(filename,realname,startpage,endpage,type);
        return ResultGenerator.genSuccessResult("转换成功！");
    }

}
