package com.yotop.aipc.controller;

import com.yotop.aipc.core.Result;
import com.yotop.aipc.core.ResultGenerator;
import com.yotop.aipc.model.AiCmmdata;
import com.yotop.aipc.service.AiCmmdataService;
import com.yotop.aipc.core.Pager;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by zzh on 2020/06/12.
*/
@RestController
@RequestMapping("/ai/cmmdata")
public class AiCmmdataController {
    @Resource
    private AiCmmdataService aiCmmdataService;

    @PostMapping
    public Result add(@RequestBody AiCmmdata aiCmmdata) {
        aiCmmdataService.save(aiCmmdata);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        aiCmmdataService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody AiCmmdata aiCmmdata) {
        aiCmmdataService.update(aiCmmdata);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        AiCmmdata aiCmmdata = aiCmmdataService.findById(id);
        return ResultGenerator.genSuccessResult(aiCmmdata);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<AiCmmdata> list = aiCmmdataService.findAll();
        Pager pager = new Pager(list);
        return ResultGenerator.genSuccessResult(pager);
    }


    /**
     * @Author: wx
     * @Description: 查询 cmm数据  刚导入的pdf信息
     * @DateTime: 2020/6/15 9:44
     * @Params: 
     * @Return 
     */
    @GetMapping("/findCmmDataByfig")
    public Result findCmmDataByfig() throws Exception{
        List<AiCmmdata> aiCmmdata = aiCmmdataService.findCmmDataByfig();
        return ResultGenerator.genSuccessResult(aiCmmdata);
    }
}
