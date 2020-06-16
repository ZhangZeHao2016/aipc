package com.yotop.aipc.controller;

import com.yotop.aipc.core.Result;
import com.yotop.aipc.core.ResultGenerator;
import com.yotop.aipc.model.AiUser;
import com.yotop.aipc.service.AiUserService;
import com.yotop.aipc.core.Pager;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by zzh on 2020/05/22.
*/
@RestController
@RequestMapping("/ai/user")
public class AiUserController {
    @Resource
    private AiUserService aiUserService;

    @PostMapping
    public Result add(@RequestBody AiUser aiUser) {
        aiUserService.save(aiUser);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        aiUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody AiUser aiUser) {
        aiUserService.update(aiUser);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        AiUser aiUser = aiUserService.findById(id);
        return ResultGenerator.genSuccessResult(aiUser);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<AiUser> list = aiUserService.findAll();
        Pager pager = new Pager(list);
        return ResultGenerator.genSuccessResult(pager);
    }
}
