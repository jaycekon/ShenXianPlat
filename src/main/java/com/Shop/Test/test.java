package com.Shop.Test;

import com.Shop.beans.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.ObjectOutput;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/7/3.
 */
public class test {
    @Test
    public void test(){
        User user = new User();
        user.setPhone("2123123");
        user.setPassword("12344");
        Class  cla = user.getClass();
        Field[] fields = cla.getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            try {
                System.out.println(field.getName()+","+field.get(user));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
