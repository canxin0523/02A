package com.pinyougo.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.pinyougo.pojo.Result;
import com.pinyougo.pojo.Specification;
import com.pinyougo.pojo.TbSpecification;
import com.sellergoods.service.SpeciService;
import entity.PageResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 0523 on 2018/9/5.
 */
@RestController
@RequestMapping("speci")
public class SpeciController {
        @Reference
        private SpeciService speciservice;

        @RequestMapping("search")
        public PageResult search(@RequestBody TbSpecification tbSpecification, int page, int rows){
            return speciservice.search(tbSpecification,page,rows);
        }
        /**
         * 增加
         * @param specification
         * @return
         */
        @RequestMapping("/add")
        public Result add(@RequestBody Specification specification){

                try {
                        speciservice.add(specification);
                        return new Result(true, "增加成功");
                } catch (Exception e) {
                        e.printStackTrace();
                        return new Result(false, "增加失败");
                }
        }
        /*
        删除方法    传入的是一个long类型的一个数组   然后调用后台的方法 就好   后台和前面的差不了太多  最麻烦的是前台页面   唉  加油
         */
        @RequestMapping("delete")
        public Result delete(Long [] ids ){
                try {
                        speciservice.delete(ids);
                        return new Result(true, "删除成功");
                } catch (Exception e) {
                        e.printStackTrace();
                        return new Result(false, "删除失败");
                }
        }
        /*
        查询一个对象
        修改的回显
         */
        @RequestMapping("/findOne")
        public Specification findOne(Long id){

                return speciservice.findOne(id);
        }
        /**
         * 修改
         * @param specification
         * @return
         */
        @RequestMapping("/update")
        public Result update(@RequestBody Specification specification){
                try {
                        speciservice.update(specification);
                        return new Result(true, "修改成功");
                } catch (Exception e) {
                        e.printStackTrace();
                        return new Result(false, "修改失败");
                }
        }



}
