package com.rqy.product.product;

import com.rqy.product.constant.Constans;
import com.rqy.product.mapper.BrokerMessageLogMapper;
import com.rqy.product.module.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author renqingyang
 * @create 2020/4/19 9:14 PM
 */
@Component
public class RabbitOrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    //创建回调函数
    final RabbitTemplate.ConfirmCallback confirmCallback= new RabbitTemplate.ConfirmCallback() {

        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("correlationData: " + correlationData);
            System.out.println("ack: " + ack);

            String messageId = correlationData.getId();
            if(!ack){
                System.out.println("异常处理....");
            }else {
                //更新消息状态
                brokerMessageLogMapper.updateStatusAndUpdateTimeByMessageId(messageId,new Date(), Constans.ORDER_SEND_SUCCESS);
            }
        }

    };

    //发送消息方法调用: 构建Message消息
    public void send(Order order) throws Exception {

        rabbitTemplate.setConfirmCallback(confirmCallback);
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend(
                "order-exchange",
                "order.123",
                order, correlationData);
    }


}
