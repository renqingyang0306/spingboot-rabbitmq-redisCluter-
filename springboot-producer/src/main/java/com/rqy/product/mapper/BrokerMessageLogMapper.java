package com.rqy.product.mapper;
import java.util.List;

import com.rqy.product.module.BrokerMessageLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @Author renqingyang
 * @create 2020/4/19 7:44 PM
 */
public interface BrokerMessageLogMapper {
    int deleteByPrimaryKey(String messageId);

    int insert(BrokerMessageLog record);

    int insertSelective(BrokerMessageLog record);

    BrokerMessageLog selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(BrokerMessageLog record);

    int updateByPrimaryKey(BrokerMessageLog record);

    int updateStatusAndUpdateTimeByMessageId(@Param("messageId") String messageId, @Param("updateTime") Date updateTime, @Param("status") Integer status);

    int updateTryCountAndUpdateTimeByMessageId(@Param("updatedUpdateTime")Date updatedUpdateTime,@Param("messageId")String messageId);

    List<BrokerMessageLog> queryAllByStatusAndNextRetry();


}