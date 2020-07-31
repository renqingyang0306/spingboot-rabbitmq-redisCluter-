package com.rqy.product.service;

import com.alibaba.fastjson.JSON;
import com.rqy.product.constant.Constans;
import com.rqy.product.mapper.BrokerMessageLogMapper;
import com.rqy.product.module.BrokerMessageLog;
import com.rqy.product.product.OrderSender;
import com.rqy.product.product.RabbitOrderSender;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rqy.product.mapper.OrderMapper;
import com.rqy.product.module.Order;

import java.util.Date;

/**
 * 
 * @Author renqingyang
 * @create 2020/4/19 9:31 PM
 */
@Service
public class OrderServiceImpl implements OrderService{

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Resource
    private RabbitOrderSender rabbitOrderSender;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Order order) {

        Date orderTime = new Date();
        int result = 0 ;
        try{
            result = orderMapper.insert(order);
            BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
            brokerMessageLog.setMessageId(order.getMessageId());
            brokerMessageLog.setMessage(JSON.toJSONString(order));
            brokerMessageLog.setStatus(0);//消息默认发送中
            brokerMessageLog.setNextRetry(DateUtils.addMinutes(orderTime, Constans.ORDER_TIMEOUT));
            brokerMessageLog.setCreateTime(new Date());
            brokerMessageLog.setUpdateTime(new Date());

            result = brokerMessageLogMapper.insertSelective(brokerMessageLog);
            //发送消息
            rabbitOrderSender.send(order);

            return result;
        } catch (Exception e){
            logger.error("Exceptipon insert "+e);
        }
        return result;
    }

    @Override
    public int insertSelective(Order record) {
        return orderMapper.insertSelective(record);
    }

    @Override
    public Order selectByPrimaryKey(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Order record) {
        return orderMapper.updateByPrimaryKey(record);
    }

}
