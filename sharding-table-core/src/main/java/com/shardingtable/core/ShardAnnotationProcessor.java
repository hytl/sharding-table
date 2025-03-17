package com.shardingtable.core;


import com.shardingtable.core.annotation.UniversalShard;
import com.shardingtable.core.strategy.ShardStrategy;
import com.shardingtable.core.strategy.UniversalShardStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 注解处理器
 *
 * @author hytl
 */
public class ShardAnnotationProcessor {
    public static ShardStrategy resolveStrategy(Class<?> entityClass, Map<String, Object> paramMap) {
        if (entityClass.isAnnotationPresent(UniversalShard.class)) {
            UniversalShard annotation = entityClass.getAnnotation(UniversalShard.class);
            List<ShardKeyConfig> shardKeyConfigs = parseKeyConfigs(annotation.shardFields(), paramMap);
            return new UniversalShardStrategy(
                    annotation.logicTable(),
                    shardKeyConfigs,
                    annotation.combiner(),
                    annotation.connector()
            );
        }
        // 其他策略处理...

        return null;
    }

    private static List<ShardKeyConfig> parseKeyConfigs(UniversalShard.ShardField[] keys, Map<String, Object> paramMap) {
        return Arrays.stream(keys)
                .map(k -> new ShardKeyConfig(
                        k.name(),
                        paramMap.get(k.name()),
                        k.rule(),
                        k.converter()
                ))
                .collect(Collectors.toList());
    }
}