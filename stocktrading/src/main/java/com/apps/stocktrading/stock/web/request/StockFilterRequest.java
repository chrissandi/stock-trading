package com.apps.stocktrading.stock.web.request;

import com.apps.stocktrading.base.web.request.CommonFilterRequest;

import java.math.BigDecimal;

public class StockFilterRequest extends CommonFilterRequest {
    private String stockCode;
    private String companyName;
    private int currentStartPrice;
    private int currentEndPrice;
    private int previousStartPrice;
    private int previousEndPrice;
    private BigDecimal marketStartCap;
    private BigDecimal marketEndCap;
    private String sector;
    private String industry;

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

    public int getCurrentStartPrice() {
        return currentStartPrice;
    }

    public void setCurrentStartPrice(int currentStartPrice) {
        this.currentStartPrice = currentStartPrice;
    }

    public int getCurrentEndPrice() {
        return currentEndPrice;
    }

    public void setCurrentEndPrice(int currentEndPrice) {
        this.currentEndPrice = currentEndPrice;
    }

    public int getPreviousStartPrice() {
        return previousStartPrice;
    }

    public void setPreviousStartPrice(int previousStartPrice) {
        this.previousStartPrice = previousStartPrice;
    }

    public int getPreviousEndPrice() {
        return previousEndPrice;
    }

    public void setPreviousEndPrice(int previousEndPrice) {
        this.previousEndPrice = previousEndPrice;
    }

    public BigDecimal getMarketStartCap() {
        return marketStartCap;
    }

    public void setMarketStartCap(BigDecimal marketStartCap) {
        this.marketStartCap = marketStartCap;
    }

    public BigDecimal getMarketEndCap() {
        return marketEndCap;
    }

    public void setMarketEndCap(BigDecimal marketEndCap) {
        this.marketEndCap = marketEndCap;
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
}
