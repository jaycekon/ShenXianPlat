package com.Shop.service;

import com.Shop.DTO.ImageDto;
import com.Shop.beans.Image;
import com.Shop.beans.Product;
import com.Shop.dao.ImageDao;
import com.Shop.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public class GoodService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ImageDao imageDao;

    public void addProduct(Product product){
        product.setCreateDate(new Date());
        productDao.addAnyType(product);
    }


    public void updateProduct(Product product){
        productDao.deleteAnyType(product);
    }

    public void deleteProduct(Product product){
        productDao.deleteAnyType(product);
    }

    public Product findProductById(int id){
        return productDao.findById(id,"Product");
    }

    public List<Product> findProductByIndex(){
        return productDao.findProductByIndex();
    }

    public List<Product> findProductByCate(String cate){
        return productDao.findProductByCate(cate);
    }

    public List<Product> findProductByName(String name){
        return productDao.findProductByName(name);
    }

    public List<ImageDto> findImageByProductId(int productId){
        List<Image> images = imageDao.findByProductId(productId);
        List<ImageDto> list = new ArrayList<>();
        for(Image image:images){
            ImageDto imageDto = new ImageDto();
            imageDto.setImageUrl(image.getImageUrl());
            list.add(imageDto);
        }
        return list;
    }


}
