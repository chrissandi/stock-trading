package com.apps.stocktrading.transaction.web.request;

import com.apps.stocktrading.base.web.request.CommonFilterRequest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class TransactionFilterRequest extends CommonFilterRequest {
    private String username;
    private String stockCode;
    private String orderNumber;
    private String orderVerb;
    private BigDecimal startQuantity;
    private BigDecimal endQuantity;
    private int startPrice;
    private int endPrice;
    private BigDecimal startTotalTransactionValue;
    private BigDecimal endTotalTransactionValue;

    private ZonedDateTime createdStartDate;

    private ZonedDateTime createdEndDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderVerb() {
        return orderVerb;
    }

    public void setOrderVerb(String orderVerb) {
        this.orderVerb = orderVerb;
    }

    public BigDecimal getStartQuantity() {
        return startQuantity;
    }

    public void setStartQuantity(BigDecimal startQuantity) {
        this.startQuantity = startQuantity;
    }

    public BigDecimal getEndQuantity() {
        return endQuantity;
    }

    public void setEndQuantity(BigDecimal endQuantity) {
        this.endQuantity = endQuantity;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(int endPrice) {
        this.endPrice = endPrice;
    }

    public BigDecimal getStartTotalTransactionValue() {
        return startTotalTransactionValue;
    }

    public void setStartTotalTransactionValue(BigDecimal startTotalTransactionValue) {
        this.startTotalTransactionValue = startTotalTransactionValue;
    }

    public BigDecimal getEndTotalTransactionValue() {
        return endTotalTransactionValue;
    }

    public void setEndTotalTransactionValue(BigDecimal endTotalTransactionValue) {
        this.endTotalTransactionValue = endTotalTransactionValue;
    }

    public ZonedDateTime getCreatedStartDate() {
        return createdStartDate;
    }

    public void setCreatedStartDate(ZonedDateTime createdStartDate) {
        this.createdStartDate = createdStartDate;
    }

    public ZonedDateTime getCreatedEndDate() {
        return createdEndDate;
    }

    public void setCreatedEndDate(ZonedDateTime createdEndDate) {
        this.createdEndDate = createdEndDate;
    }
}
