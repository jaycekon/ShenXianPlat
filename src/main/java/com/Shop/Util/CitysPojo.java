package com.Shop.Util;

import com.Shop.beans.Citys;
import com.Shop.beans.Province;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class CitysPojo {
    private Province province;
    private List<Citys> citys;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<Citys> getCitys() {
        return citys;
    }

    public void setCitys(List<Citys> citys) {
        this.citys = citys;
    }
}
