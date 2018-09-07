package com.pinyougo.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougo.pojo.Result;
import com.pinyougo.pojo.TbTypeTemplate;
import com.sellergoods.service.TypeTemplateService;
import entity.PageResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 0523 on 2018/9/6.
 */
@RestController
@RequestMapping("typetem")
public class TypeTemplateController {
    @Reference
    private TypeTemplateService typeTemplateService;

    @RequestMapping("search")
    public PageResult search(@RequestBody TbTypeTemplate tbTypeTemplate, int page, int rows){
        return typeTemplateService.search(tbTypeTemplate,page,rows);
    }
}
