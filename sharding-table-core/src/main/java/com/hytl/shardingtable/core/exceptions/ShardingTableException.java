package com.hytl.shardingtable.core.exceptions;

/**
 * ShardingTableException
 *
 * @author hytl
 */
public class ShardingTableException extends RuntimeException {
    public ShardingTableException() {
        super();
    }

    public ShardingTableException(String message) {
        super(message);
    }

    public ShardingTableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShardingTableException(Throwable cause) {
        super(cause);
    }
}
