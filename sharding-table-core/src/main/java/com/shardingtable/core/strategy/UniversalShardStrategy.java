package com.shardingtable.core.strategy;

import com.shardingtable.core.ShardKeyConfig;
import com.shardingtable.core.annotation.Prototype;
import com.shardingtable.core.combiner.ShardValueConvertValuesCombiner;
import com.shardingtable.core.convertor.ShardValueConvertor;
import com.shardingtable.core.exceptions.ShardValueConvertNullResultException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通用分表策略
 *
 * @author hytl
 */
public class UniversalShardStrategy implements ShardStrategy {
    private final Map<Class<? extends ShardValueConvertor>, ShardValueConvertor> SHARD_VALUE_CONVERTOR_INSTANCE_MAP = new ConcurrentHashMap<>();
    private final Map<Class<? extends ShardValueConvertValuesCombiner>, ShardValueConvertValuesCombiner> SHARD_FIELD_VALUES_COMBINER_INSTANCE_MAP = new ConcurrentHashMap<>();

    private final String logicTable;
    private final List<ShardKeyConfig> keyConfigs;
    private final Class<? extends ShardValueConvertValuesCombiner> shardFiledValuesCombinerClass;
    private final String connector;

    public UniversalShardStrategy(String logicTable
            , List<ShardKeyConfig> keyConfigs
            , Class<? extends ShardValueConvertValuesCombiner> shardFiledValuesCombiner
            , String connector) {
        this.logicTable = logicTable;
        this.keyConfigs = keyConfigs;
        this.shardFiledValuesCombinerClass = shardFiledValuesCombiner;
        this.connector = connector;

        // Check
        if (logicTable == null || logicTable.isEmpty()) {
            throw new IllegalArgumentException("logic table name is required");
        }
        if (keyConfigs == null || keyConfigs.isEmpty()) {
            throw new IllegalArgumentException("shardFields are required");
        }
        if (shardFiledValuesCombiner == null) {
            throw new IllegalArgumentException("combiner is required");
        }
        if (connector == null) {
            throw new IllegalArgumentException("connector is required");
        }
    }

    @Override
    public String actualTableName() {
        ShardValueConvertValuesCombiner shardFiledValuesCombiner;
        if (shardFiledValuesCombinerClass.isAnnotationPresent(Prototype.class)) {
            try {
                shardFiledValuesCombiner = shardFiledValuesCombinerClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            shardFiledValuesCombiner = SHARD_FIELD_VALUES_COMBINER_INSTANCE_MAP.computeIfAbsent(shardFiledValuesCombinerClass, key -> {
                try {
                    return shardFiledValuesCombinerClass.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return logicTable + connector + shardFiledValuesCombiner.combine(keyConfigs.stream().map(shardKeyConfig -> {
            Class<? extends ShardValueConvertor> shardKeyConfigConvertorClass = shardKeyConfig.getConvertor();
            ShardValueConvertor shardValueConvertor;
            if (shardKeyConfigConvertorClass.isAnnotationPresent(Prototype.class)) {
                try {
                    shardValueConvertor = shardKeyConfigConvertorClass.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                shardValueConvertor = SHARD_VALUE_CONVERTOR_INSTANCE_MAP.computeIfAbsent(shardKeyConfigConvertorClass, key -> {
                    try {
                        return shardKeyConfigConvertorClass.newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            String convertResult = shardValueConvertor.convert(shardKeyConfig.getValue(), shardKeyConfig.getRule());
            if (convertResult == null) {
                throw new ShardValueConvertNullResultException("The conversion result for field " + shardKeyConfig.getField() + " is null");
            }
            return convertResult;
        }).toArray(String[]::new));
    }

}