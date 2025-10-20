package org.xxpay.shardingdemo.mapper;

import org.apache.ibatis.annotations.*;
import org.xxpay.shardingdemo.entity.PayOrder;

import java.util.List;

@Mapper
public interface PayOrderMapper {

    @Insert("INSERT INTO t_pay_order(payOrderId, merchantId, amount,status, createTime) " +
            "VALUES(#{orderId}, #{merchantId}, #{amount}, #{status}, #{createTime})")
    int insert(PayOrder order);

    @Select("SELECT * FROM t_pay_order WHERE payOrderId = #{orderId}")
    PayOrder findById(String orderId);

    @Select("SELECT * FROM t_pay_order WHERE merchantId = #{merchantId}")
    List<PayOrder> findByMerchantID(String merchantId);

    @Update("UPDATE t_pay_order SET status = #{status} WHERE payOrderId = #{orderId}")
    int updateStatus(String orderId,  Integer status);

    @Delete("DELETE FROM t_pay_order WHERE payOrderId = #{orderId}")
    int delete(String orderId);
}