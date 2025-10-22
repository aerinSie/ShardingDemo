package org.xxpay.shardingdemo.mapper;

import org.apache.ibatis.annotations.*;
import org.xxpay.shardingdemo.entity.PayOrder;

import java.util.List;

@Mapper
public interface PayOrderHistMapper {

    @Insert("INSERT INTO t_pay_order_history(payOrderId, merchantId, catchId, amount, status, createTime" +
            ",merchantOrderNo, notifyUrl,currency) " +
            "VALUES(#{order.payOrderId}, #{order.merchantId},#{order.catchId}, #{order.amount}, #{order.status}," +
            " #{order.createTime},#{order.payOrderId} ,#{order.payOrderId} ,#{order.currency})")
    int insert(@Param("order" )PayOrder order);

    @Select("SELECT * FROM t_pay_order_history WHERE payOrderId = #{orderId}")
    PayOrder findById(String orderId);

    @Select("SELECT * FROM t_pay_order_history WHERE merchantId = #{merchantId} and currency = #{currency}")
    List<PayOrder> findByMerchantID(String merchantId, String currency);

    @Update("UPDATE t_pay_order_history SET status = #{status} WHERE payOrderId = #{orderId}")
    int updateStatus(String orderId,  Integer status);

    @Delete("DELETE FROM t_pay_order_history WHERE payOrderId = #{orderId}")
    int delete(String orderId);
}