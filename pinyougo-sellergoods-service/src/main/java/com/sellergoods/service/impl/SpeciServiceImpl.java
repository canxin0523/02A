package com.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougo.mapper.TbSpecificationMapper;
import com.pinyougo.mapper.TbSpecificationOptionMapper;
import com.pinyougo.pojo.*;
import com.sellergoods.service.SpeciService;

import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 0523 on 2018/9/5.
 */
@Service
public class SpeciServiceImpl implements SpeciService {

    @Autowired
    private TbSpecificationOptionMapper  specificationOptionMapper;
    @Autowired
    private TbSpecificationMapper specificationMapper;

    @Override
    public PageResult search(TbSpecification tbSpecification, int page, int rows) {
        PageHelper.startPage(page, rows);
        TbSpecificationExample example=new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();
        if(tbSpecification!=null){
            if(tbSpecification.getSpecName()!=null && tbSpecification.getSpecName().length()>0){
                criteria.andSpecNameLike("%"+tbSpecification.getSpecName()+"%");
            }
        }
        Page<TbSpecification> pageone= (Page<TbSpecification>) specificationMapper.selectByExample(example);
        return new PageResult(pageone.getTotal(), pageone.getResult());

    }
        /**
         * 增加  for 这一步没看懂到底是什么意思  唉
         */
        @Override
        public void add(Specification specification) {
            specificationMapper.insert(specification.getSpecification());//插入规格
            //循环插入规格选项
            for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){
                specificationOption.setSpecId(specification.getSpecification().getId());//设置规格ID
                 		specificationOptionMapper.insert(specificationOption);
            }
        }
        /**
         * 批量删除
         */
        @Override
        public void delete(Long[] ids) {
            for(Long id:ids){
                specificationMapper.deleteByPrimaryKey(id);
                //删除原有的规格选项
                TbSpecificationOptionExample example=new TbSpecificationOptionExample();
                com.pinyougo.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
                criteria.andSpecIdEqualTo(id);//指定规格ID为条件
                specificationOptionMapper.deleteByExample(example);//删除
            }
        }

    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    @Override
    public Specification findOne(Long id){
        //查询规格
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        //查询规格选项列表
        TbSpecificationOptionExample example=new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);//根据规格ID查询
        List<TbSpecificationOption> optionList = specificationOptionMapper.selectByExample(example);
        //构建组合实体类返回结果
        Specification spec=new Specification();
        spec.setSpecification(tbSpecification);
        spec.setSpecificationOptionList(optionList);
        return spec;
    }

    /**
     * 修改之保存
     * @param specification
     */
    @Override
    public void update(Specification specification) {
        //保存修改的规格
        specificationMapper.updateByPrimaryKey(specification.getSpecification());//保存规格
        //删除原有的规格选项
        TbSpecificationOptionExample example=new TbSpecificationOptionExample();
        com.pinyougo.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());//指定规格ID为条件
        specificationOptionMapper.deleteByExample(example);//删除
        //循环插入规格选项
        for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){
            specificationOption.setSpecId(specification.getSpecification().getId());
            specificationOptionMapper.insert(specificationOption);
        }

    }


}
