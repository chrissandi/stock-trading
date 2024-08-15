package com.apps.stocktrading.transaction.repository.mapper;

import com.apps.stocktrading.transaction.dto.TransactionDTO;
import com.apps.stocktrading.transaction.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TransactionEntityMapper {
    TransactionDTO toDto(TransactionEntity transactionEntity);
    TransactionEntity toEntity(TransactionDTO transactionDTO);

    List<TransactionDTO> toDto(List<TransactionEntity> transactionEntity);

}
