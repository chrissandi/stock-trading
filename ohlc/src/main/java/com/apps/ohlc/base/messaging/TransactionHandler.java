package com.apps.ohlc.base.messaging;


import com.apps.ohlc.calculator.dto.TransactionDTO;
import com.apps.ohlc.calculator.service.CalculatorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TransactionHandler {
    private final CalculatorService calculatorService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = Logger.getLogger(TransactionHandler.class.getName());

    public TransactionHandler(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @Async
    @KafkaListener(topics = "transaction",groupId = "trx-group")
    public void onEvent(String message) throws JsonProcessingException {
        TransactionDTO transactionDTO = objectMapper.readValue(message,TransactionDTO.class);
        if((transactionDTO.getOrderVerb()!=null && transactionDTO.getOrderVerb().equalsIgnoreCase("B"))||transactionDTO.getQuantity()==0){
            calculatorService.addTransaction(transactionDTO);
        }
    }
}
