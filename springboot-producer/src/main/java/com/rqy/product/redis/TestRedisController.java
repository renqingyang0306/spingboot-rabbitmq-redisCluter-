package com.rqy.product.redis;

/**
 * @Author renqingyang
 * @create 2020/6/18 7:50 PM
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redis")
public class TestRedisController {
    @Autowired
    RedisUtil redisUtil;

    /**
     * 生产者 通过此方法来往redis的list的尾部插入数据
     */
    @RequestMapping("/product")
    public void shengChangZhe() {
        redisUtil.product();
    }

    /**
     * 消费者 ，通过此方法往redis的list的头部获取数据，直到list没有数据 阻塞，等到一有数据又继续获取
     */
    @RequestMapping("/consumer")
    public void xiaoFeiZhe() {
        redisUtil.consumer();
    }

    /**
     * 发布者，发布信息，监听器一旦接收到监听，就进行操作
     */
    @RequestMapping("/faBuDingYue")
    public void faBuDingYue() {
        redisUtil.dealFaBuDingYue();
    }

}

