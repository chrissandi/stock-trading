package com.apps.stocktrading.transaction.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class SimpleTransactionDTO {
    private String orderVerb;
    private BigDecimal quantity;
    private int price;
    private BigDecimal totalTransactionValue;

    private ZonedDateTime createdDate;

    public String getOrderVerb() {
        return orderVerb;
    }

    public void setOrderVerb(String orderVerb) {
        this.orderVerb = orderVerb;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public BigDecimal getTotalTransactionValue() {
        return totalTransactionValue;
    }

    public void setTotalTransactionValue(BigDecimal totalTransactionValue) {
        this.totalTransactionValue = totalTransactionValue;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
