package com.cn.pay.service.impl;


import com.cn.pay.PayApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class PayServiceTest extends PayApplicationTests {

    @Autowired
    private PayServiceImpl payService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void create() {

        payService.create("4154085412318451251", BigDecimal.valueOf(0.01));

    }

    @Test
    public void sendMQMsg() {
        amqpTemplate.convertAndSend("payNotify","hello");
    }


}
