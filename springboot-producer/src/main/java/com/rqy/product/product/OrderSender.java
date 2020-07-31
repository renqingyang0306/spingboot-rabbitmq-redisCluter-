package com.rqy.product.product;

import com.rqy.product.module.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author renqingyang
 * @create 2020/4/18 11:22 PM
 */
@Component
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Order order) throws Exception{

        CorrelationData data = new CorrelationData();
        data.setId(order.getMessageId());
        rabbitTemplate.convertAndSend(
                   "order-exchange",//exchange
                   "order.abc",//路由key
                   order,//消息体
                   data//消息的唯一ID
           );
    }
}
