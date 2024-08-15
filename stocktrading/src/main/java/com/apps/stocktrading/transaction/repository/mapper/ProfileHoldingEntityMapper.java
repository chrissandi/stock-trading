package com.apps.stocktrading.transaction.repository.mapper;

import com.apps.stocktrading.transaction.dto.ProfileHoldingDTO;
import com.apps.stocktrading.transaction.dto.SimpleTransactionDTO;
import com.apps.stocktrading.transaction.dto.TransactionDTO;
import com.apps.stocktrading.transaction.entity.ProfileHoldingEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProfileHoldingEntityMapper {
    ObjectMapper objectMapper = new ObjectMapper();

    @Mapping(target = "simpleTransactionList", source = "simpleTransactionList", qualifiedByName = "jsonStringToSimpleTransactionDto")
    ProfileHoldingDTO toDto(ProfileHoldingEntity ProfileHoldingEntity);
    @Mapping(target = "simpleTransactionList", source = "simpleTransactionList", qualifiedByName = "simpleTransactionDtoToJsonString")
    ProfileHoldingEntity toEntity(ProfileHoldingDTO ProfileHoldingDTO);

    SimpleTransactionDTO toSimpleTransactionDto(TransactionDTO transactionDTO);
    @Named("simpleTransactionDtoToJsonString")
    default String transactionDtoToJsonString(List<SimpleTransactionDTO> simpleTransactionDtoList) throws IOException {
        if(Objects.nonNull(simpleTransactionDtoList)){
            return new String(objectMapper.writeValueAsBytes(simpleTransactionDtoList));
        }
        return null;
    }

    @Named("jsonStringToSimpleTransactionDto")
    default List<SimpleTransactionDTO> jsonStringToSimpleTransactionDto(String simpleTransactionList) throws IOException {
        if(Objects.nonNull(simpleTransactionList)){
            List<SimpleTransactionDTO> dto = objectMapper.readValue(simpleTransactionList, new TypeReference<List<SimpleTransactionDTO>>() {});
            return dto;
        }
        return null;
    }
}
