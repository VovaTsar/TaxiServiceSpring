package com.mytaxi.model.service.mappers;

import com.mytaxi.model.domain.Coupon;
import com.mytaxi.model.entities.CouponEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CouponMapper {
    Coupon couponEntitytoCoupon(CouponEntity couponEntity);
    CouponEntity couponToCouponEntity(Coupon coupon);
}
