package com.mytaxi.model.repository;

import com.mytaxi.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByDriverStatusAndCarCarType(String driverStatus, String carType);

    Optional<UserEntity>findByPhoneNumberAndPassword(String phoneNumber, String password);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

}
