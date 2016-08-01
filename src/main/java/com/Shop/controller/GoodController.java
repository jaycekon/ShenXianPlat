package com.Shop.controller;

import com.Shop.DTO.DetailDto;
import com.Shop.Util.ProductPojo;
import com.Shop.beans.Image;
import com.Shop.beans.Product;
import com.Shop.service.GoodService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
@Controller
public class GoodController {
    @Autowired
    private GoodService goodService;

    @RequestMapping(value="addProduct",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String productAdd(Product product){
        goodService.addProduct(product);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status","true");
        return jsonObject.toString();
    }


    @RequestMapping(value="/Detail",method = RequestMethod.GET)
    @ResponseBody
    public DetailDto productDetail(int id){
        Product product = goodService.findProductById(id);
        List<String> list = goodService.findImageByProductId(product.getId());
        DetailDto detailDto = new DetailDto();
        detailDto.setProduct(product);
        detailDto.setImageUrl(list);
        return detailDto;
    }



}
