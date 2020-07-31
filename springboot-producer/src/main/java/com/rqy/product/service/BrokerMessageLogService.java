package com.rqy.product.service;

import com.rqy.product.module.BrokerMessageLog;
    /**
 * 
 * @Author renqingyang
 * @create 2020/4/19 7:44 PM
 */
public interface BrokerMessageLogService{


    int deleteByPrimaryKey(String messageId);

    int insert(BrokerMessageLog record);

    int insertSelective(BrokerMessageLog record);

    BrokerMessageLog selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(BrokerMessageLog record);

    int updateByPrimaryKey(BrokerMessageLog record);

}
