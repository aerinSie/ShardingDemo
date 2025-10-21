package org.xxpay.shardingdemo;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.xxpay.shardingdemo.entity.PayOrder;
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
     * SELECT * FROM t_pay_order_history_0 WHERE merchantId = ? and catchId = ?
     * UNION ALL
     * SELECT * FROM t_pay_order_history_1 WHERE merchantId = ? and catchId = ?
     */
    @Test
    void getOrderListFromAllTable() {
        var orders = service.findByMerchantID("a501","AAPAY");
        System.out.println("=============明細===============");
        orders.forEach(System.out::println);
    }

    /**
     * 以 payOrderId 的餘數分表存入 t_pay_order_history
     * 須配合 執行設定檔 application.properties_payOrderId 為 application.properties
     */
//    @Test
//    void insertOrder() {
//        String orderId = "Order" + new Date().getTime();
//        String merchantId = "a501";
//        String catchId = "AAPAY";
//        service.createOrder(orderId, merchantId , BigDecimal.valueOf(12345), catchId);
//        System.out.println("=============success===============");
//    }


    /**
     * 以 雙主鍵 merchantId+catchId 的餘數分表存入 t_pay_order_history
     * 須配合 執行設定檔 application.yml_merchantId_and_catchId 為 application.yml
     */
    @Test
    void insertOrder() {
        String orderId = "Order" + new Date().getTime();
        String merchantId = "a501";
        String catchId = "AAPAY"; // 理論會存入 t_pay_order_history_1
        service.createOrder(orderId, merchantId , BigDecimal.valueOf(12345), catchId);


        catchId = "8BQPAY"; // 理論會存入 t_pay_order_history_0
        service.createOrder(orderId, merchantId , BigDecimal.valueOf(12345), catchId);
        System.out.println("=============success===============");
    }
}
