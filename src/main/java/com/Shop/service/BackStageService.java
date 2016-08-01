package com.Shop.service;

import com.Shop.beans.Cate;
import com.Shop.beans.Citys;
import com.Shop.beans.Province;
import com.Shop.dao.CateDao;
import com.Shop.dao.CitysDao;
import com.Shop.dao.ProvinceDao;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class BackStageService {
    @Autowired
    private CitysDao citysDao;
    @Autowired
    private ProvinceDao provinceDao;
    @Autowired
    private CateDao cateDao;

    public String addCitys(Citys citys){
        List<Citys> cityses = citysDao.findByProvince(citys.getPro());
        JsonObject jsonObject = new JsonObject();
        for(Citys citys1:cityses){
            if (citys1.getName().equals(citys.getName())){
                jsonObject.addProperty("status",false);
                jsonObject.addProperty("message","城市已存在");
                return jsonObject.toString();
            }
        }
        citysDao.addAnyType(citys);
        jsonObject.addProperty("status",true);
        return jsonObject.toString();
    }

    public String  addProvince(Province province){
        JsonObject  jsonObject = new JsonObject();
        Province p = provinceDao.findByName(province.getName());
        if (p !=null){
            jsonObject.addProperty("status",false);
            jsonObject.addProperty("message","分类以存在");
            return jsonObject.toString();
        }
        provinceDao.addAnyType(province);
        jsonObject.addProperty("status",true);
        return jsonObject.toString();
    }

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

    public Province findProvince(int id){
        return provinceDao.findById(id,"Province");
    }

    public List<Citys> findCitys(String pro){
        return citysDao.findByProvince(pro);
    }

    public List<Province> findProvince(){
        return provinceDao.findAll("Province");
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
