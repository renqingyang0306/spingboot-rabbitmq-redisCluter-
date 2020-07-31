package com.rqy.product;

import com.alibaba.fastjson.JSONObject;
import com.rqy.product.module.Order;
import com.rqy.product.product.OrderSender;
import com.rqy.product.service.OrderService;
import com.rqy.product.util.RestResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringbootProducerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private OrderSender orderSender;
    @Autowired
    private OrderService orderService;
    @Test
    void setOrderSender() throws Exception {
        Order order = new Order();
        order.setId(100023212);
        order.setName("测试订单12321");
        order.setMessageId("GUHKJsHJIHDAISDAfsdfD123");


        //orderSender.send(order);
        orderService.insert(order);
    }
    @Test
    public void test(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "hello");
        map.put("value", "cno,name,queueName,gno,groupName");
        map.put("type", "20012");
        map.put("userType", 0);
        map.put("userId", 1);
        map.put("callCenterId", "20009");
        String jsonString = JSONObject.toJSONString(map);
        HashMap<String, Object> result = JSONObject.parseObject(jsonString,HashMap.class);
        String format = String.format("custom_query_condition_callCenterId.%s.type.%s.userType.%s.userId.%s", map.get("callCenterId"), map.get("type"), map.get("userType"), map.get("userId"));
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(result);
        RestResponse res = new RestResponse();
        res.setMsg("成功！");
        res.setResult(list);
        res.setStatus(HttpStatus.OK.value());
        System.out.println(result.toString());
        System.out.println(res.toJSON());
        System.out.println(format);
    }


}
