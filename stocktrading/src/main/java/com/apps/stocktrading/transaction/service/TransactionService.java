package com.apps.stocktrading.transaction.service;

import com.apps.stocktrading.transaction.dto.TransactionDTO;
import com.apps.stocktrading.transaction.web.request.TransactionFilterRequest;

import java.util.List;

public interface TransactionService {
    TransactionDTO createTransaction(TransactionDTO transactionDTO);
    List<TransactionDTO> filterTransaction(TransactionFilterRequest request);

}
