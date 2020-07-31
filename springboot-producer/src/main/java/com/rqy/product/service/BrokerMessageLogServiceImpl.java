package com.rqy.product.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rqy.product.module.BrokerMessageLog;
import com.rqy.product.mapper.BrokerMessageLogMapper;
import com.rqy.product.service.BrokerMessageLogService;
/**
 * 
 * @Author renqingyang
 * @create 2020/4/19 7:44 PM
 */
@Service
public class BrokerMessageLogServiceImpl implements BrokerMessageLogService{

    @Resource
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Override
    public int deleteByPrimaryKey(String messageId) {
        return brokerMessageLogMapper.deleteByPrimaryKey(messageId);
    }

    @Override
    public int insert(BrokerMessageLog record) {
        return brokerMessageLogMapper.insert(record);
    }

    @Override
    public int insertSelective(BrokerMessageLog record) {
        return brokerMessageLogMapper.insertSelective(record);
    }

    @Override
    public BrokerMessageLog selectByPrimaryKey(String messageId) {
        return brokerMessageLogMapper.selectByPrimaryKey(messageId);
    }

    @Override
    public int updateByPrimaryKeySelective(BrokerMessageLog record) {
        return brokerMessageLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BrokerMessageLog record) {
        return brokerMessageLogMapper.updateByPrimaryKey(record);
    }

}
