package com.apps.stocktrading.transaction.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "profile_holding")
public class ProfileHoldingEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "holding_id", columnDefinition = "uuid", updatable = false, nullable = false, insertable = false)
    private UUID holdingId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "stock_code", nullable = false)
    private String stockCode;

    @Column(name = "simple_transaction_list")
    private String simpleTransactionList;

    @Column(name = "average_purchase_price")
    private double averagePurchasePrice;

    @Column(name = "total_investment_value")
    private BigDecimal totalInvestmentValue;

    @Column(name = "total_quantity")
    private BigDecimal totalQuantity;

    @CreationTimestamp
    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private java.sql.Timestamp lastModifiedDate;

    @Column(name = "deleted_date")
    private java.sql.Timestamp deletedDate;

    @Column(name = "deleted_flag")
    private boolean deletedFlag;

    public UUID getHoldingId() {
        return holdingId;
    }

    public void setHoldingId(UUID holdingId) {
        this.holdingId = holdingId;
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

    public String getSimpleTransactionList() {
        return simpleTransactionList;
    }

    public void setSimpleTransactionList(String simpleTransactionList) {
        this.simpleTransactionList = simpleTransactionList;
    }

    public int getAveragePurchasePrice() {
        return averagePurchasePrice;
    }

    public void setAveragePurchasePrice(int averagePurchasePrice) {
        this.averagePurchasePrice = averagePurchasePrice;
    }

    public BigDecimal getTotalInvestmentValue() {
        return totalInvestmentValue;
    }

    public void setTotalInvestmentValue(BigDecimal totalInvestmentValue) {
        this.totalInvestmentValue = totalInvestmentValue;
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    public boolean isDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(boolean deletedFlag) {
        this.deletedFlag = deletedFlag;
    }
}