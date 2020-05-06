package com.arcsoft.docserver.aqapserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author xqh3622
 */
@RestController
public class RediesController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("setData")
    public void setData() {

        LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        //assert
        if(lettuceConnectionFactory != null){
        lettuceConnectionFactory.setDatabase(0);

        lettuceConnectionFactory.resetConnection();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        }
        System.out.println("开始时间"+ new Date());

        int index = 100000000;
        for (int i = 0; i < index; i++) {
            redisTemplate.opsForValue().set(DigestUtils.md5DigestAsHex(String.valueOf(i).getBytes()),i);
        }
        System.out.println("结束时间"+ new Date());

    }
    @RequestMapping("getData")
    public void getData(Integer key){
        String hex = DigestUtils.md5DigestAsHex(String.valueOf(key).getBytes());
        System.out.println(hex);
        int test = (int) redisTemplate.opsForValue().get(hex);
        //Properties info = redisTemplate.getRequiredConnectionFactory().getConnection().info("memory");
        //System.out.println(info+"内存占用情况");
        System.out.println(test);
    }




//    public static void main(String[] args) {
//        String  str = "2033";
//        String hex = DigestUtils.md5DigestAsHex(str.getBytes());
//        System.out.println(hex);
//
//    }


}


