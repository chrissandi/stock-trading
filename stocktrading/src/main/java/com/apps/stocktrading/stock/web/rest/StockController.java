package com.apps.stocktrading.stock.web.rest;
import com.apps.stocktrading.stock.dto.StockDTO;
import com.apps.stocktrading.stock.service.StockService;
import com.apps.stocktrading.stock.web.contract.StockApiContract;
import com.apps.stocktrading.stock.web.request.StockFilterRequest;
import com.apps.stocktrading.stock.web.request.StockUpdateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(StockApiContract.STOCK)
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping()
    public StockDTO createStock(@RequestBody StockDTO stock) {
        return stockService.createStock(stock);
    }

    @GetMapping("/{stockCode}")
    public StockDTO getStock(@PathVariable String stockCode) {
        return stockService.getStockByStockCode(stockCode);
    }

    @PostMapping(StockApiContract.UPDATE)
    public StockDTO updateStock(@RequestBody StockUpdateRequest request) {
        return stockService.updateStock(request);
    }

    @DeleteMapping("/{stockCode}")
    public void deleteStock(@PathVariable String stockCode) {
        stockService.deleteStock(stockCode);
    }

    @GetMapping(StockApiContract.GET_PAGED)
    public List<StockDTO> getPaged(@RequestParam StockFilterRequest request) {
        return stockService.filterStock(request);
    }
}
