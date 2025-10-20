package org.xxpay.shardingdemo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xxpay.shardingdemo.entity.PayOrder;
import org.xxpay.shardingdemo.mapper.PayOrderMapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayOrderService {


    private final PayOrderMapper mapper;

    public void createOrder(String orderId, String merchantId, BigDecimal amount) {
        PayOrder order = new PayOrder();
        order.setPayOrderId(orderId);
        order.setMerchantId(merchantId);
        order.setAmount(amount);
        order.setStatus(0);
        order.setCreateTime(new Date());
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

    public List<PayOrder> findByMerchantID(String userId) {
        return mapper.findByMerchantID(userId);
    }
}
