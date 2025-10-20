package org.xxpay.shardingdemo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.xxpay.shardingdemo.entity.PayOrder;
import org.xxpay.shardingdemo.service.PayOrderHistService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/hist/orders")
@RequiredArgsConstructor
public class PayOrderHistController {

    private final PayOrderHistService service;

    @PostMapping
    public String create(@RequestParam String orderId,
                         @RequestParam String merchantId,
                         @RequestParam BigDecimal amount) {
        service.createOrder(orderId, merchantId, amount);
        return "OK";
    }

    @GetMapping("/{orderId}")
    public PayOrder get(@PathVariable String orderId) {
        return service.getOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public String update(@PathVariable String orderId, @RequestParam Integer status) {
        service.updateOrderStatus(orderId, status);
        return "Updated";
    }

    @DeleteMapping("/{orderId}")
    public String delete(@PathVariable String orderId) {
        service.deleteOrder(orderId);
        return "Deleted";
    }

    @GetMapping("/merchantId/{merchantId}")
    public List<PayOrder> list(@PathVariable String merchantId) {
        return service.findByMerchantID(merchantId);
    }
}