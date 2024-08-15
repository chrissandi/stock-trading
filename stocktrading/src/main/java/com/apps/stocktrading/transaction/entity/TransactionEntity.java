package com.apps.stocktrading.transaction.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction", uniqueConstraints = @UniqueConstraint(columnNames = {"transaction_id"}))
public class TransactionEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "transaction_id", columnDefinition = "uuid", updatable = false, nullable = false, insertable = false)
    private UUID transactionId;
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "stock_code", nullable = false)
    private String stockCode;

    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @Column(name = "order_verb", nullable = false)
    private String orderVerb;

    @Column(name = "quantity", nullable = false)
    private BigDecimal quantity;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "total_transaction_value", nullable = false)
    private BigDecimal totalTransactionValue;

    @Column(name = "created_date")
    public ZonedDateTime createdDate;

    @Column(name = "last_modified_date")
    public ZonedDateTime lastModifiedDate;

    @Column(name = "deleted_flag")
    public boolean deletedFlag;

    @Column(name = "deleted_date")
    public ZonedDateTime deletedDate;

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

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

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean isDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(boolean deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public ZonedDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(ZonedDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }
}
