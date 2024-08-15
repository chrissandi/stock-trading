package com.apps.stocktrading.transaction.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProfileHoldingDTO {
    private UUID holdingId;
    private UUID profileId;
    private UUID stockId;
    private List<SimpleTransactionDTO> simpleTransactionList;
    private double averagePurchasePrice;
    private BigDecimal totalInvestmentValue;
    private BigDecimal totalQuantity;

    public UUID getHoldingId() {
        return holdingId;
    }

    public void setHoldingId(UUID holdingId) {
        this.holdingId = holdingId;
    }

    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    public UUID getStockId() {
        return stockId;
    }

    public void setStockId(UUID stockId) {
        this.stockId = stockId;
    }

    public List<SimpleTransactionDTO> getSimpleTransactionList() {
        return simpleTransactionList;
    }

    public void setSimpleTransactionList(List<SimpleTransactionDTO> simpleTransactionList) {
        this.simpleTransactionList = simpleTransactionList;
    }

    public double getAveragePurchasePrice() {
        return averagePurchasePrice;
    }

    public void setAveragePurchasePrice(double averagePurchasePrice) {
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
}
