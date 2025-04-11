package com.hytl.shardingtable.core.convertor;

/**
 * 默认转换器实现
 *
 * @author hytl
 */
public class DefaultShardValueConverter implements ShardValueConvertor {
    @Override
    public String convert(Object keyValue, String rule) {
        return keyValue.toString();
    }
}