package com.apps.stocktrading.transaction.repository;

import com.apps.stocktrading.transaction.entity.ProfileHoldingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileHoldingRepository extends JpaRepository<ProfileHoldingEntity, UUID> {
    Optional<ProfileHoldingEntity> findByProfileIdAndStockId(String username, String stockCode);
}
