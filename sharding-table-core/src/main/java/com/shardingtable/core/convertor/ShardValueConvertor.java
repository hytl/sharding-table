package com.shardingtable.core.convertor;

/**
 * 分表键值转换接口
 */
@FunctionalInterface
public interface ShardValueConvertor<V> {
    String convert(V value, String rule);
}
