package com.hytl.shardingtable.core.first;

import com.hytl.shardingtable.core.ShardAnnotationProcessor;
import com.hytl.shardingtable.core.strategy.ShardStrategy;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * OrderTest
 *
 * @author hytl
 */
public class OrderTest {

    @Test
    public void orderTest() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", "123");
        paramMap.put("time", LocalDateTime.now());
        ShardStrategy shardStrategy = ShardAnnotationProcessor.resolveStrategy(Order.class, paramMap);
        System.out.println(shardStrategy.actualTableName()); // order_123_202503
    }
}
