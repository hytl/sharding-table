package com.shardingtable.core.strategy;

/**
 * 分表策略
 *
 * @author hytl
 */
@FunctionalInterface
public interface ShardStrategy {
    /**
     * 获取实际表名
     *
     * @return 实际表名
     */
    String actualTableName();
}
