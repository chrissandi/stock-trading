package com.apps.stocktrading.stock.entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "stock", uniqueConstraints = @UniqueConstraint(columnNames = {"stock_id","stock_code"}))
public class StockEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "stock_id", columnDefinition = "uuid", updatable = false, nullable = false, insertable = false)
    private UUID stockId;

    @Column(name = "stock_code", length = 10, nullable = false)
    private String stockCode;

    @Column(name = "company_name", length = 100)
    private String companyName;

    @Column(name = "current_price", nullable = false)
    private int currentPrice;
    @Column(name = "previous_price", nullable = false)
    private int previousPrice;

    @Column(name = "market_cap", nullable = false)
    private BigDecimal marketCap;

    @Column(name = "sector", length = 50, nullable = false)
    private String sector;

    @Column(name = "industry", length = 50, nullable = false)
    private String industry;

    @Column(name = "stock_description", nullable = false)
    private String stockDescription;

    @Column(name = "created_date")
    public ZonedDateTime createdDate;

    @Column(name = "last_modified_date")
    public ZonedDateTime lastModifiedDate;

    @Column(name = "deleted_flag")
    public boolean deletedFlag;

    @Column(name = "deleted_date")
    public ZonedDateTime deletedDate;

    public UUID getStockId() {
        return stockId;
    }

    public void setStockId(UUID stockId) {
        this.stockId = stockId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getPreviousPrice() {
        return previousPrice;
    }

    public void setPreviousPrice(int previousPrice) {
        this.previousPrice = previousPrice;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getStockDescription() {
        return stockDescription;
    }

    public void setStockDescription(String stockDescription) {
        this.stockDescription = stockDescription;
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
