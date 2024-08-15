package com.apps.stocktrading.stock.service;


import com.apps.stocktrading.stock.dto.StockDTO;
import com.apps.stocktrading.stock.web.request.StockFilterRequest;
import com.apps.stocktrading.stock.web.request.StockUpdateRequest;

import java.util.List;

public interface StockService {
    StockDTO createStock(StockDTO stock);
    StockDTO getStockByStockCode(String stockCode);
    StockDTO updateStock(StockUpdateRequest request);
    void deleteStock(String stockCode);
    List<StockDTO> filterStock(StockFilterRequest request);
}
