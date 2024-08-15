package com.apps.ohlc.calculator.controller;

import com.apps.ohlc.calculator.dto.OhlcDTO;
import com.apps.ohlc.calculator.service.CalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ohlc")
public class OhlcController {
    private final CalculatorService calculatorService;

    public OhlcController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/{stockCode}")
    public OhlcDTO getStockSummary(@PathVariable String stockCode){
        return calculatorService.getStockSummary(stockCode);
    }
}
