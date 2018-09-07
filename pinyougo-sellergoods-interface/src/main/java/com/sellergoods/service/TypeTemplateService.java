package com.sellergoods.service;

import com.pinyougo.pojo.TbTypeTemplate;
import entity.PageResult;

/**
 * Created by 0523 on 2018/9/6.
 */
public interface TypeTemplateService {
        public PageResult search(TbTypeTemplate tbTypeTemplate,int page,int rows);

}
