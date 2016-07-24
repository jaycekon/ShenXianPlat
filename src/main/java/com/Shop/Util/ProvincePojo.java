package com.Shop.Util;

import com.Shop.beans.Citys;
import com.Shop.beans.Province;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class ProvincePojo {
    private List<Province> provinces;
    private List<Citys> cityses;


    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public List<Citys> getCityses() {
        return cityses;
    }

    public void setCityses(List<Citys> cityses) {
        this.cityses = cityses;
    }
}
