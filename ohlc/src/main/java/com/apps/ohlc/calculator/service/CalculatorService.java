package com.apps.ohlc.calculator.service;

import com.apps.ohlc.calculator.dto.OhlcDTO;
import com.apps.ohlc.calculator.dto.TransactionDTO;

public interface CalculatorService {
    OhlcDTO getStockSummary(String stockCode);
    OhlcDTO addTransaction(TransactionDTO transactionDTO);
}
