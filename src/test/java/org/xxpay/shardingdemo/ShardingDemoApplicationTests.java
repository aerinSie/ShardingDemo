package org.xxpay.shardingdemo;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.xxpay.shardingdemo.service.PayOrderHistService;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
class ShardingDemoApplicationTests {

    @Autowired
    private PayOrderHistService service;

    /**
     * Actual SQL:
     * SELECT * FROM t_pay_order_history_0 WHERE payOrderId = ?
     */
    @Test
    void getOrderFromTable0() {
        var order = service.getOrder("erin0066");
        System.out.println("=============單號明細===============");
        System.out.println(order);
    }

    /**
     * Actual SQL:
     * SELECT * FROM t_pay_order_history_1 WHERE payOrderId = ?
     */
    @Test
    void getOrderFromTable1() {
        var order = service.getOrder("erin003");
        System.out.println("=============單號明細===============");
        System.out.println(order);
    }

    /**
     * Actual SQL:
     * <p>
     * SELECT * FROM t_pay_order_history_0 WHERE merchantId = ?
     * UNION ALL
     * SELECT * FROM t_pay_order_history_1 WHERE merchantId = ?
     */
    @Test
    void getOrderListFromAllTable() {
        var order = service.findByMerchantID("a501");
        System.out.println("=============明細===============");
        System.out.println(order);
    }

    /**
     * Actual SQL:
     * INSERT INTO t_pay_order_history_1(payOrderId, merchantId, amount, status, createTime, MerchantOrderNo, NotifyUrl)
     * VALUES(?, ?, ?, ?, ?, ?, ?)
     */
    @Test
    void insertOrder() {
        service.createOrder("Order" + new Date().getTime(), "a501", BigDecimal.valueOf(12345));
        System.out.println("=============success===============");
    }

}
