package com.rqy.product.consumer;

import com.rabbitmq.client.Channel;
import com.rqy.product.module.Order;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author renqingyang
 * @create 2020/4/19 12:48 AM
 */
@Component
public class OrderReceiver {

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "order-exchange",durable = "true",type = "topic"),
            value = @Queue(value = "order_queue",durable = "true"),
            key = "order.#"
    ))
    @RabbitHandler
    public void onOrderMessage(@Payload Order order,
                               @Headers Map<String ,Object> headers,
                               Channel channel
                               ) throws Exception{

        System.out.println("订单消费了"+order.getId());
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        channel.basicAck(tag,false);

    }
}
