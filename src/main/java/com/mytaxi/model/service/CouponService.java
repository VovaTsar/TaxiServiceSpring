package com.mytaxi.model.service;

import com.mytaxi.model.domain.Coupon;

public interface CouponService {
    Coupon getCouponByCouponName( String couponName);

    Integer getDiscount( Coupon coupon);
}
