package com.Shop.Util;

import com.Shop.beans.User;
import com.Shop.dao.UserDao;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2016/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ProduceConsume {
    @Autowired
    private  UserDao userDao;
    private  RuntimeSchema<User> runtimeSchema = RuntimeSchema.createFrom(User.class);
    @Test
    public  void test() {
        User user = userDao.loadUser(1);
        setUser(user);
        getUser();
    }

    public  void getUser(){
        Jedis jedis = new Jedis("121.42.190.17");
        String key = "userId:"+2;
        byte[] bytes = jedis.get(key.getBytes());
        User user = runtimeSchema.newMessage();
        if (bytes != null) {
            ProtobufIOUtil.mergeFrom(bytes,user,runtimeSchema);
            System.out.print(user.toString());
        }
    }

    public  void setUser(User user){
        Jedis jedis = new Jedis("121.42.190.17");
        String key = "userId:"+2;
        user.setSex("男");
        user.setEmail("jaycekon@163.com");
        user.setId(1);
        user.setPhone("18814127384");
        user.setPassword("1234");
        user.setImg("1234");
        byte[] bytes = ProtobufIOUtil.toByteArray(user,runtimeSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        int timeout = 60 * 60;
        String result = jedis.setex(key.getBytes(),timeout,bytes);
        System.out.println(result);
    }


}