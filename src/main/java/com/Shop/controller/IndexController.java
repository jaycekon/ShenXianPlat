package com.Shop.controller;

import com.Shop.Util.ProductListPoJo;
import com.Shop.Util.ProductPojo;
import com.Shop.beans.Product;
import com.Shop.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
@Controller
public class IndexController {
    @Autowired
    private GoodService goodService;

    @RequestMapping(value="/index",method = RequestMethod.GET)
    @ResponseBody
    public ProductListPoJo index(){
        List<Product> products =goodService.findProductByIndex();
        ProductListPoJo productListPoJo = new ProductListPoJo();
        productListPoJo.setItems(products);
        return productListPoJo;
    }
}
