package com.rqy.product.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author renqingyang
 * @create 2020/6/19 3:03 PM
 */
@Component
public class RedisTask {
//    @Resource
//    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private RedisService redisService;


    private Logger logger = LoggerFactory.getLogger(RedisTask.class);

    @Scheduled(initialDelay = 3000,fixedDelay = 10000)
    public void operate(){
        logger.info("-----------------定时任务开始执行--------------------");
        //taskExecutor.submit(() ->{
            //读取队列信息
            try{

                Object ceshi = redisService.lPop("ceshi");
                if (!Objects.isNull(ceshi)) {
                    System.out.println("任务消费了：{}" + ceshi.toString());
                }

            }catch (Exception e){
                logger.error("定时任务失败：{} ", e);
            }
        //});
    }
}
