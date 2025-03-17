package com.shardingtable.core.first;

import com.shardingtable.core.annotation.UniversalShard;
import com.shardingtable.core.annotation.UniversalShard.ShardField;
import com.shardingtable.core.convertor.LocalDateTimeConvertor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@UniversalShard(logicTable = "order", shardFields = {
        @ShardField(name = "user_id")
        , @ShardField(name = "time", rule = "yyyyMM", converter = LocalDateTimeConvertor.class)})
public class Order {
    private Long orderId;
    private String userId;
    private BigDecimal amount;
    private LocalDateTime time;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}