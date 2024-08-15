package com.apps.stocktrading.base.messaging;

import com.apps.stocktrading.transaction.dto.StreamedTransactionDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {
    private final KafkaTemplate<String, StreamedTransactionDTO> kafkaTemplate;
    private static final String TOPIC = "transaction";


    public TransactionProducer(KafkaTemplate<String, StreamedTransactionDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(StreamedTransactionDTO message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
