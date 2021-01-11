package io.auth.api.repository;

import io.auth.api.domain.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {
    Optional<PartnerEntity> findById(String id);
    Long countById(String id);
}
