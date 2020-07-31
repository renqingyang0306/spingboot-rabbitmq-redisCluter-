package com.rqy.product.service;

import com.rqy.product.module.Order;
    /**
 * 
 * @Author renqingyang
 * @create 2020/4/19 9:31 PM
 */
public interface OrderService{


    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

}
