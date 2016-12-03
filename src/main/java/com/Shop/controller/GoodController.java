package com.Shop.controller;

import com.Shop.DTO.CommentDto;
import com.Shop.DTO.DetailDto;
import com.Shop.DTO.ImageDto;
import com.Shop.DTO.IndexDto;
import com.Shop.Util.ProductPojo;
import com.Shop.beans.Comment;
import com.Shop.beans.Image;
import com.Shop.beans.Product;
import com.Shop.service.CommentService;
import com.Shop.service.GoodService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/6/30.
 */
@Controller
public class GoodController {
    @Autowired
    private GoodService goodService;
    @Autowired
    private CommentService commentService;
        Logger logger = Logger.getLogger(this.getClass());





    @RequestMapping(value="/Detail",method = RequestMethod.GET)
    @ResponseBody
    public DetailDto productDetail(int id){
        Product product = goodService.findProductById(id);
        List<ImageDto> list = goodService.findImageByProductId(product.getId());
        List<Comment> commentDtos =commentService.findCommentByGoodId(id);
        DetailDto detailDto = new DetailDto();
        detailDto.setProduct(product);
        detailDto.setImageUrl(list);
        detailDto.setComments(commentDtos);
        return detailDto;
    }

    @RequestMapping(value="findProduct",method={RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object findProduct(String name){
        List<Product> products = goodService.findProductByName(name);
        logger.info(name);
        IndexDto indexDtos = new IndexDto();
        if(products!=null){
            indexDtos.setItems(products);
            indexDtos.setStatus(0);
        }else{
            indexDtos.setStatus(1);
            indexDtos.setErrorMsg("找不到相关商品");
        }
        return indexDtos;
    }











}
