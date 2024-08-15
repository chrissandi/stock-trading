package com.apps.stocktrading.base.serializer;

import com.apps.stocktrading.transaction.dto.StreamedTransactionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class StreamedTransactionDtoSerializer implements Serializer<StreamedTransactionDTO> {

    @Override
    public byte[] serialize(String topic, StreamedTransactionDTO data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing StreamedTransactionDTO: " + e.getMessage(), e);
        }
    }
}