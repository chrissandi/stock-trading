package com.apps.stocktrading.transaction.controller;


import com.apps.stocktrading.transaction.service.StreamTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/stocktrading")
public class StreamTransactionController {
    private final StreamTransactionService transactionService;

    public StreamTransactionController(StreamTransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/start-streaming")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void startStreaming() {
        try {
            transactionService.streamTransactionData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
