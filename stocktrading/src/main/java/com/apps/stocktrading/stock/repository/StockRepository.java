package com.apps.stocktrading.stock.repository;

import com.apps.stocktrading.stock.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StockRepository extends JpaRepository<StockEntity, UUID> {
    Optional<StockEntity> findByStockCode(String stockCode);
}
