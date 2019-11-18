package com.mytaxi.model.repository;

import com.mytaxi.model.entities.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {
    CouponEntity findByCouponName(String couponName);
}
