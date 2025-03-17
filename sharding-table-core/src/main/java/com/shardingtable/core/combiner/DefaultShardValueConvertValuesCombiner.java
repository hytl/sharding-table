package com.shardingtable.core.combiner;

/**
 * 默认分表键值组合器
 *
 * @author hytl
 */
public class DefaultShardValueConvertValuesCombiner implements ShardValueConvertValuesCombiner {
    @Override
    public String combine(String[] values) {
        if (values == null || values.length < 1) {
            throw new IllegalArgumentException("values is empty");
        }
        return String.join("_", values);
    }
}
