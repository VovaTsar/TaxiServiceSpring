package com.mytaxi.model.service.impl;

import com.mytaxi.model.domain.Coupon;
import com.mytaxi.model.service.CouponService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class CouponServiceImpl implements CouponService {
    @Override
    public Optional<Coupon> getCouponByCouponName(String couponName) {
        return Optional.empty();
    }

    @Override
    public Integer getDiscount(Coupon coupon) {
        return null;
    }
}
