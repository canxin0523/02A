package com.sellergoods.service;

import com.pinyougo.pojo.Specification;
import com.pinyougo.pojo.TbSpecification;
import entity.PageResult;

/**
 * Created by 0523 on 2018/9/5.
 */
public interface SpeciService {
    public PageResult search(TbSpecification tbSpecification,int page,int rows);
    /*
    新增
     */
    public void add(Specification specification);
    /*
    删除方法
     */
    public void delete(Long[] ids);
    /*
    修改之回显
     */
    public Specification findOne(Long id);
    /*
    修改之保存
     */
    public void update(Specification specification);

}
