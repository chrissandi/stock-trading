package com.apps.ohlc.calculator.dto;



public class TransactionDTO {
    private String orderNumber;
    private String orderVerb;
    private String orderBook;
    private String stockCode;
    private int quantity;
    private int price;

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

    public String getOrderBook() {
        return orderBook;
    }

    public void setOrderBook(String orderBook) {
        this.orderBook = orderBook;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
