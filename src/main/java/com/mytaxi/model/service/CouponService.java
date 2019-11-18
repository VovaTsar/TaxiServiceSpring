package com.mytaxi.model.service;

import com.mytaxi.model.domain.Coupon;

import java.util.Optional;

public interface CouponService {
    Optional<Coupon> getCouponByCouponName( String couponName);

    Integer getDiscount( Coupon coupon);
}
