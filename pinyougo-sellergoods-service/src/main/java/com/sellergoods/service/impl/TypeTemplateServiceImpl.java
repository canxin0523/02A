package com.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougo.mapper.TbTypeTemplateMapper;
import com.pinyougo.pojo.TbTypeTemplate;
import com.pinyougo.pojo.TbTypeTemplateExample;
import com.sellergoods.service.TypeTemplateService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 0523 on 2018/9/6.
 *         /*
 @Override
 public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
 PageHelper.startPage(pageNum, pageSize);
 TbBrandExample example=new TbBrandExample();
 TbBrandExample.Criteria criteria = example.createCriteria();
 if(brand!=null){
 if(brand.getName()!=null && brand.getName().length()>0){
 criteria.andNameLike("%"+brand.getName()+"%");
 }
 if(brand.getFirstChar()!=null && brand.getFirstChar().length()>0){
 criteria.andFirstCharEqualTo(brand.getFirstChar());
 }
 }
 Page<TbBrand> page= (Page<TbBrand>)brandMapper.selectByExample(example);
 return new PageResult(page.getTotal(), page.getResult());
 }
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {
    @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;

    @Override
    public PageResult search(TbTypeTemplate tbTypeTemplate, int page, int rows) {
        // 分页
        PageHelper.startPage(page, rows);
        //条件查询
        TbTypeTemplateExample example = new TbTypeTemplateExample();
        TbTypeTemplateExample.Criteria criteria = example.createCriteria();
        if(tbTypeTemplate  != null){
            if(tbTypeTemplate.getName() != null && tbTypeTemplate.getName().length()>0) {
                criteria.andNameLike("%" + tbTypeTemplate.getName() + "%");
            }
        }
        // page 类   总条数  和   结果  list
        Page<TbTypeTemplate> pageone = (Page<TbTypeTemplate>) tbTypeTemplateMapper.selectByExample(example);
        return new PageResult(pageone.getTotal(),pageone.getResult());
    }
}
