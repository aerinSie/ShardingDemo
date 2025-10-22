package org.xxpay.shardingdemo.sharding;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.*;

@Slf4j
public class Merchant501TableShardingAlgorithm implements StandardShardingAlgorithm<String> {

    @Override
    public void init(Properties properties) {
        Object prop = properties.get("prop");
        log.info("配置信息：{}", JSON.toJSONString(prop));
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames,
                             PreciseShardingValue<String> shardingValue) {

        log.info("進入精准分片 precise availableTargetNames:{}", JSON.toJSONString(availableTargetNames));
        log.info("進入精准分片 preciseShardingValue:{}", JSON.toJSONString(shardingValue));

        String merchantId = shardingValue.getValue();
        String tablePrefix = shardingValue.getDataNodeInfo().getPrefix();

        if ("a501".equals(merchantId)) {
            return tablePrefix + "0"; // table_0
        } else {
            // 隨機進入 table_1 ~ table_9
            var size = availableTargetNames.size();
            return switch (size) {
                case 0 -> tablePrefix + "0";
                case 1 -> tablePrefix + "1";
                default -> {
                    int randomIndex = (Math.abs(merchantId.hashCode()) % (size - 1)) + 1;
                    yield tablePrefix + randomIndex;
                }
            };
        }
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
                                         RangeShardingValue<String> shardingValue) {
        // 針對 BETWEEN、<、> 等範圍查詢，暫時返回所有表（可以根據需要優化）
        log.info("進入範圍分片：range availableTargetNames:{}", JSON.toJSONString(availableTargetNames));
        log.info("進入範圍分片：rangeShardingValue:{}", JSON.toJSONString(shardingValue));
        return new HashSet<>(availableTargetNames);
    }

    @Override
    public String getType() {
        return "STANDARD";
    }

    @Override
    public Properties getProps() {
        return new Properties();
    }
}
