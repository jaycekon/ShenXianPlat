package com.Shop.service;

import com.Shop.beans.Address;
import com.Shop.beans.User;
import com.Shop.dao.AddressDao;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class AddressService {
    @Autowired
    private AddressDao addressDao;

    public void addAddress(Address address,int userId){
        if(address.getFlag()==1){
            Address a = addressDao.findAddressByFlag(1,userId);
            if(a!=null) {
                a.setFlag(0);
                addressDao.updateAnyType(a);
            }
        }
        addressDao.addAnyType(address);
    }

    public void updateAddress(Address address,int userId){
        if(address.getId()==0){
            addAddress(address,userId);
            return;
        }
        if(address.getFlag()==1){
            Address a = addressDao.findAddressByFlag(1,userId);
            a.setFlag(0);
            addressDao.updateAnyType(a);
        }
        addressDao.updateAnyType(address);
    }

    public String deleteAddress(int id){
        JsonObject jsonObject =new JsonObject();
        Address address = findAddressById(id);
        if(address==null){
            jsonObject.addProperty("status",1);
            jsonObject.addProperty("message","找不到地址");
            return jsonObject.toString();
        }
        addressDao.deleteAnyType(address);
        jsonObject.addProperty("status",0);
        return jsonObject.toString();
    }

    public List<Address> findAddressByUuserId(int userId){
        List<Address> addresses = addressDao.findAddressByUserId(userId);
        return addresses;
    }

    public Address findAddressById(int id){
        return addressDao.findById(id,"Address");
    }

    public Address findAddressByFlag(int userId){
        return addressDao.findAddressByFlag(1,userId);
    }

}
