package com.cn.pay.service.impl;

import com.cn.pay.service.IPayService;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class PayServiceImpl implements IPayService {

    @Autowired
    private BestPayService  bestPayService;

    @Override
    public PayResponse create(String orderId, BigDecimal amount) {
        // 写入数据库

        PayRequest request = new PayRequest();
        request.setOrderName("7138507-最好的支付SDK");
        request.setOrderId(orderId);
        request.setOrderAmount(amount.doubleValue());
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_NATIVE);

        bestPayService.pay(request);

        PayResponse response = bestPayService.pay(request);
        log.info("response={}",response);

        return response;
    }

    @Override
    public String asyncNotify(String notifyData) {
        // 1.签名校验
        PayResponse response = bestPayService.asyncNotify(notifyData);
        log.info("payResponse={}",response);

        // 2.金额校验（从数据库查订单）

        // 3.修改订单支付状态

        // 4.告诉微信不要在通知了
        return "";
    }
}
