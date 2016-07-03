package com.Shop.controller;

import com.Shop.Util.CitysPojo;
import com.Shop.Util.ProvincePojo;
import com.Shop.beans.Cate;
import com.Shop.beans.Citys;
import com.Shop.beans.Province;
import com.Shop.service.BackStageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "listProvince",method = RequestMethod.GET)
    @ResponseBody
    public ProvincePojo listProvince(){
        List<Province> provinces = backStageService.findProvince();
        ProvincePojo provincePojo = new ProvincePojo();
        provincePojo.setProvinces(provinces);
        return provincePojo;
    }

    @RequestMapping(value="listCitys",method = RequestMethod.GET)
    @ResponseBody
    public CitysPojo listCitys(int id){
        Province province = backStageService.findProvince(id);
        List<Citys> cityses = backStageService.findCitys(province.getName());
        CitysPojo citysPojo = new CitysPojo();
        citysPojo.setCitys(cityses);
        citysPojo.setProvince(province);
        return citysPojo;
    }

    @RequestMapping(value = "addProvince",method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String addProvince(Province province){
        String json = backStageService.addProvince(province);
        return json;
    }
    @RequestMapping(value = "addCitys",method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String addCitys(Citys citys){
        String json = backStageService.addCitys(citys);
        return json;
    }

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
