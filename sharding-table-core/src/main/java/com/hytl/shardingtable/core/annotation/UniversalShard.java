package com.hytl.shardingtable.core.annotation;

import com.hytl.shardingtable.core.combiner.DefaultShardValueConvertValuesCombiner;
import com.hytl.shardingtable.core.combiner.ShardValueConvertValuesCombiner;
import com.hytl.shardingtable.core.convertor.DefaultShardValueConverter;
import com.hytl.shardingtable.core.convertor.ShardValueConvertor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通用分表策略注解
 *
 * @author hytl
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UniversalShard {
    /**
     * 逻辑表名
     */
    String logicTable() default "";

    /**
     * 逻辑表名和分表键转换合并字符串之间连接符（默认为_）
     */
    String connector() default "_";

    /**
     * 分表键配置数组
     */
    ShardField[] shardFields();

    /**
     * 分表键值组合器
     */
    Class<? extends ShardValueConvertValuesCombiner> combiner() default DefaultShardValueConvertValuesCombiner.class;

    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    @interface ShardField {
        /**
         * 分表键字段名
         */
        String name();

        /**
         * 转换规则
         */
        String rule() default "";

        /**
         * 转换器实现类
         */
        Class<? extends ShardValueConvertor> converter() default DefaultShardValueConverter.class;
    }
}