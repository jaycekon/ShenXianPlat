package com.Shop.service;

import com.Shop.beans.Cate;
import com.Shop.dao.CateDao;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class BackStageService {
    @Autowired
    private CateDao cateDao;


    public String addCate(Cate cate){
        Cate c = cateDao.findByCateName(cate.getName());
        JsonObject jsonObject = new JsonObject();
        if(c!=null){
            jsonObject.addProperty("status",false);
            jsonObject.addProperty("message","已存在分类");
            return jsonObject.toString();
        }
        cateDao.addAnyType(cate);
        jsonObject.addProperty("status",true);
        return jsonObject.toString();
    }

    public String addCates(Cate cate){
        Cate c = cateDao.findByCateNameAndId(cate.getName(),cate.getCate().getId());
        JsonObject jsonObject = new JsonObject();
        if(c!=null){
            jsonObject.addProperty("status",false);
            jsonObject.addProperty("message","已存在分类");
            return jsonObject.toString();
        }
        cateDao.addAnyType(cate);
        jsonObject.addProperty("status",true);
        return jsonObject.toString();
    }

    public Cate findCateByName(String name){
        Cate cate = cateDao.findByCateName(name);
        return cate;
    }

    public Cate findCateById(int id){
        Cate cate = cateDao.findById(id,"Cate");
        return cate;
    }
}
