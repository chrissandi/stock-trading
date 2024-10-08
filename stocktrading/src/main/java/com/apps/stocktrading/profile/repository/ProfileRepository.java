package com.apps.stocktrading.profile.repository;

import com.apps.stocktrading.profile.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface ProfileRepository extends JpaRepository<ProfileEntity, UUID> {
    Optional<ProfileEntity> findByUsername(String username);
    Optional<ProfileEntity> findByEmail(String email);
}
