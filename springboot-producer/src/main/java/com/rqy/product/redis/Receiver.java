package com.rqy.product.redis;

/**
 * @Author renqingyang
 * @create 2020/6/18 7:56 PM
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service("receiver")
public class Receiver{

    @Autowired
    private RedisService redisService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 清除外部广告位本地缓存
     * @param message
     */
    public  void dealJt(String message){
        System.out.println("我是用来监听信息的");
        System.out.println(message);
    }

    /**
     * 清除外部广告位本地缓存
     * @param message
     */
    public  void dealJt1(String message){
        System.out.println("我是用来监听信息的1");
        System.out.println(message);
    }


}

