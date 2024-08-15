package com.apps.stocktrading.transaction.service.impl;

import com.apps.stocktrading.base.messaging.TransactionProducer;
import com.apps.stocktrading.transaction.dto.StreamedTransactionDTO;
import com.apps.stocktrading.transaction.service.StreamTransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Service
public class StreamTransactionServiceImpl implements StreamTransactionService {
    private static final Logger logger = Logger.getLogger(StreamTransactionService.class.getName());
    private final TransactionProducer producer;

    public StreamTransactionServiceImpl(TransactionProducer producer) {
        this.producer = producer;
    }

    @Override
    public void streamTransactionData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String directoryPath = "src/main/resources/transaction_data/";

        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath), FileVisitOption.FOLLOW_LINKS)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".ndjson"))
                    .forEach(path -> {
                        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                StreamedTransactionDTO transactionDto = objectMapper.readValue(line, StreamedTransactionDTO.class);
                                logger.info("DATA: Stock Code:"+transactionDto.getStockCode()+
                                        " Quantity:"+transactionDto.getQuantity()+" Price:"+transactionDto.getPrice());
                                producer.sendMessage(transactionDto);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }
}
