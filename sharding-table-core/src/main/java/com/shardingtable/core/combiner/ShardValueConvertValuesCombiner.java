package com.shardingtable.core.combiner;

/**
 * 分表键转换值组合接口
 *
 * @author hytl
 */
@FunctionalInterface
public interface ShardValueConvertValuesCombiner {
    String combine(String[] values);
}
