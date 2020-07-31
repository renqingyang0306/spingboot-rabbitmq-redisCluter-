package com.rqy.product.Task;

import com.alibaba.fastjson.JSONObject;
import com.rqy.product.constant.Constans;
import com.rqy.product.mapper.BrokerMessageLogMapper;
import com.rqy.product.module.BrokerMessageLog;
import com.rqy.product.module.Order;
import com.rqy.product.product.RabbitOrderSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author renqingyang
 * @create 2020/4/19 10:06 PM
 */
@Component
public class RetryMessageTask {

    @Resource
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Resource
    private RabbitOrderSender rabbitOrderSender;


    @Scheduled(initialDelay = 3000,fixedDelay = 10000)
    public void reSend(){
        System.out.println("-----------------定时任务开执行--------------------");
        List<BrokerMessageLog> messageLogs = brokerMessageLogMapper.queryAllByStatusAndNextRetry();

        messageLogs.forEach(messageLog ->{

            if (messageLog.getTryCount() >=3){
                //认为消息消费失败
                brokerMessageLogMapper.updateStatusAndUpdateTimeByMessageId(messageLog.getMessageId(),new Date(), Constans.ORDER_SEND_FAILUER);

            } else {
                //resend
                brokerMessageLogMapper.updateTryCountAndUpdateTimeByMessageId(new Date(),messageLog.getMessageId());

                Order order = JSONObject.parseObject(messageLog.getMessage(), Order.class);
                try {
                    rabbitOrderSender.send(order);
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("------消息重送失败----------");
                }

            }

        });
    }

}
