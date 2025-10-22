package org.xxpay.shardingdemo.service;


import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.stereotype.Service;
import org.xxpay.shardingdemo.entity.PayOrder;
import org.xxpay.shardingdemo.mapper.PayOrderHistMapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayOrderHistService {


    private final PayOrderHistMapper mapper;

    public void createOrder(String orderId, String merchantId, BigDecimal amount, String catchId, String currency) {
        PayOrder order = new PayOrder();
        order.setPayOrderId(orderId);
        order.setMerchantId(merchantId);
        order.setCatchId(catchId);
        order.setAmount(amount);
        order.setStatus(0);
        order.setCreateTime(new Date());
        order.setCurrency(currency);
        mapper.insert(order);
    }

    public PayOrder getOrder(String orderId) {
        return mapper.findById(orderId);
    }

    public void updateOrderStatus(String orderId, Integer status) {
        mapper.updateStatus(orderId, status);
    }

    public void deleteOrder(String orderId) {
        mapper.delete(orderId);
    }

    public List<PayOrder> findByMerchantID(String merchantId, String currency) {
        var list= mapper.findByMerchantID(merchantId, currency);
        System.out.println("PayOrder size:"+list.size());
        return list;
    }
}
