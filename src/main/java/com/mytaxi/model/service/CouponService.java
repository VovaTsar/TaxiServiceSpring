package com.mytaxi.model.service;

import com.mytaxi.model.domain.Coupon;

import java.util.List;

public interface CouponService {
    Coupon findByCouponName( String couponName);
    List<Coupon> findAll();

}
