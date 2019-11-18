package com.mytaxi.model.repository;

import com.mytaxi.model.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    AddressEntity findByIdAddress(Long id);
}
