package com.apps.stocktrading.stock.repository.mapper;

import com.apps.stocktrading.stock.dto.StockDTO;
import com.apps.stocktrading.stock.entity.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StockEntityMapper {
    StockDTO toDto(StockEntity stockEntity);
    StockEntity toEntity(StockDTO stockDTO);
    List<StockDTO> toDto(List<StockEntity> stockEntities);
}
