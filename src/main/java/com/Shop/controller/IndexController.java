package com.Shop.controller;

import com.Shop.DTO.ImageDto;
import com.Shop.DTO.IndexDto;
import com.Shop.DTO.PictureDto;
import com.Shop.DTO.ResultDto;
import com.Shop.Util.ProductListPoJo;
import com.Shop.Util.ProductPojo;
import com.Shop.Util.UserAgentUtil;
import com.Shop.beans.Cate;
import com.Shop.beans.Product;
import com.Shop.beans.User;
import com.Shop.service.BackStageService;
import com.Shop.service.GoodService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
@Controller
public class IndexController {
    @Autowired
    private GoodService goodService;
    @Autowired
    private BackStageService backStageService;
    Logger logger = Logger.getLogger(this.getClass());
    @RequestMapping(value="/getImage",method = RequestMethod.GET)
    @ResponseBody
    public Object getImage(){
        List<ImageDto> list = goodService.findImageByProductId(0);
        PictureDto pictureDto = new PictureDto();
        pictureDto.setImageUrl(list);
        return pictureDto;
    }

    @RequestMapping(value="/index",method = RequestMethod.GET)
    @ResponseBody
    public Object index(HttpServletResponse response,HttpServletRequest request,String cate){
//        String cate =request.getParameter("cate");

        IndexDto indexDto  = new IndexDto();
        List<Product> products = new ArrayList<>();
        if(cate==null||cate.equals("")) {
            products =goodService.findProductByIndex();
        }else{
            logger.info(cate);
            products = goodService.findProductByCate(cate);
        }
        List<ImageDto> list = goodService.findImageByProductId(0);
        boolean flag = UserAgentUtil.isMobileDevice(request);
        if(!flag){
            indexDto.setImages(list);
        }
        if(products==null){
            indexDto.setStatus(1);
            indexDto.setErrorMsg("获取首页商品信息失败");
        }else {
            indexDto.setItems(products);
            indexDto.setStatus(0);
        }
//        ProductListPoJo productListPoJo = new ProductListPoJo();
//        productListPoJo.setItems(products);
//        if(flag){
//            return productListPoJo;
//        }
        return indexDto;
    }


    @RequestMapping(value="/Cate",method = RequestMethod.GET)
    @ResponseBody
    public IndexDto findByCate(String cate){
        List<Product> products =goodService.findProductByCate(cate);
        IndexDto indexDto = new IndexDto();
        indexDto.setItems(products);
        return indexDto;
    }


    @RequestMapping(value = "/getPost",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public User getJson(String name,String password){
        User user = new User();
        user.setNickname(name);
        user.setPassword(password);
        System.out.println(user.toString());
        return user;
    }
}
