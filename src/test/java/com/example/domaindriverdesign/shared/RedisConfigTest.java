package com.example.domaindriverdesign.shared;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisConfigTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Test
    public void testAdd(){
        redisTemplate.opsForValue().set("test","测试数据");
        System.out.println("添加成功");
    }

    @Test
    public void testGet(){
        String test = redisTemplate.opsForValue().get("test");
        System.out.println(test);
    }


}

