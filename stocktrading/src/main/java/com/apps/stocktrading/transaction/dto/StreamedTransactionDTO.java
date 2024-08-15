package com.apps.stocktrading.transaction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamedTransactionDTO{
    private String orderNumber;
    private String orderVerb;
    private String orderBook;
    private String stockCode;
    private int quantity;
    private int price;

    public StreamedTransactionDTO(@JsonProperty("order_number") String orderNumber,@JsonProperty("order_verb") String orderVerb,
                                  @JsonProperty("order_book") String orderBook, @JsonProperty("stock_code")String stockCode,
                                  @JsonProperty("quantity")int quantity, @JsonProperty("executed_quantity")int executedQuantity,
                                  @JsonProperty("price")int price, @JsonProperty("execution_price")int executionPrice) {
        this.orderNumber = orderNumber;
        this.orderVerb = orderVerb;
        this.orderBook = orderBook;
        this.stockCode = stockCode;
        this.quantity = (executedQuantity != 0) ? executedQuantity : quantity;
        this.price = (executionPrice != 0) ? executionPrice : price;
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
