package com.shardingtable.core.exceptions;

/**
 * 分表键值转换Null结果Exception
 * 当分表键值转换结果为null时抛出
 *
 * @author hytl
 */
public class ShardValueConvertNullResultException extends ShardingTableException {
    public ShardValueConvertNullResultException() {
    }

    public ShardValueConvertNullResultException(String message) {
        super(message);
    }

    public ShardValueConvertNullResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShardValueConvertNullResultException(Throwable cause) {
        super(cause);
    }
}
