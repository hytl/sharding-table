package com.shardingtable.core;

import com.shardingtable.core.convertor.ShardValueConvertor;

/**
 * 配置载体
 *
 * @author hytl
 */
public class ShardKeyConfig {
    private final String field;
    private final Object value;
    private final String rule;
    private final Class<? extends ShardValueConvertor> convertor;

    public ShardKeyConfig(String field, Object value, String rule, Class convertor) {
        this.field = field;
        this.value = value;
        this.rule = rule;
        this.convertor = convertor;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public String getRule() {
        return rule;
    }

    public Class<? extends ShardValueConvertor> getConvertor() {
        return convertor;
    }
}