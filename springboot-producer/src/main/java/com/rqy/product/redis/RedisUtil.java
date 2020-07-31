package com.rqy.product.redis;

/**
 * @Author renqingyang
 * @create 2020/6/18 7:50 PM
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.List;

@Component
public class RedisUtil {
    @Autowired
    JedisCluster jedisCluster;

    @Autowired
    private RedisService redisService;

    /**
     * 生产者
     */
    public void product() {
        for (int i = 0; i < 10; i++) {
            redisService.rPush("ceshi", "value1_" + i);
        }
    }

    /**
     * 消费者
     */
    public void consumer() {
        while (true) {
            //阻塞式brpop，List中无数据时阻塞，参数0表示一直阻塞下去，直到List出现数据
           // List<String> listingList = jedisCluster.blpop(0, "ceshi");

            //System.out.println("线程取数据：{}" + listingList.get(1));
            Object ceshi = redisService.lPop("ceshi");
            System.out.println("线程取数据：{}" +ceshi.toString());
        }
    }

    /**
     * 发布订阅模式
     */
    public void dealFaBuDingYue() {
        redisService.convertAndSend("dealFaBuDingYue", "我是来发布信息的");
    }
}

