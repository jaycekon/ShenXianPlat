package com.Shop.controller;

import com.Shop.beans.Cate;
import com.Shop.service.BackStageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
@Controller
public class BackStageController {
    @Autowired
    private BackStageService backStageService;
    Logger log = Logger.getLogger(this.getClass());



    @RequestMapping(value="addCate",method= RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String addCate(Cate cate, HttpServletRequest request){
        String json="";
        if(request.getParameter("cateName")!=null){
            log.info("加入二级分类");
            String cateName= request.getParameter("cateName");
            Cate c = backStageService.findCateByName(cateName);
            cate.setCate(c);
            json = backStageService.addCates(cate);
        }else {
            log.info("加入一级分类");
            json = backStageService.addCate(cate);
        }
        return json;
    }

}
