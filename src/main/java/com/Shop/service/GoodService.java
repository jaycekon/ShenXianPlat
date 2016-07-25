package com.Shop.service;

import com.Shop.beans.Product;
import com.Shop.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public class GoodService {
    @Autowired
    private ProductDao productDao;

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


}
