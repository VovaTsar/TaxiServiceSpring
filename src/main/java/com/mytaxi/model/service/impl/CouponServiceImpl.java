package com.mytaxi.model.service.impl;

import com.mytaxi.model.customExceptions.DataValidateRuntimeException;
import com.mytaxi.model.customExceptions.EntityNotFoundRuntimeException;
import com.mytaxi.model.domain.Coupon;
import com.mytaxi.model.entities.AddressEntity;
import com.mytaxi.model.entities.CouponEntity;
import com.mytaxi.model.repository.CouponRepository;
import com.mytaxi.model.service.CouponService;
import com.mytaxi.model.service.mappers.CouponMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponMapper mapper;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository, CouponMapper mapper) {
        this.couponRepository = couponRepository;
        this.mapper = mapper;
    }

    @Override
    public Coupon findByCouponName(String couponName) {
        if (Objects.isNull(couponName)){
            log.warn("CouponService: find coupon by coupon name ");
            throw new DataValidateRuntimeException("Сoupon name is null");
        }

        Optional<CouponEntity> result = couponRepository.findByCouponName(couponName);

         CouponEntity couponEntity =  result.orElseThrow(() -> {
            log.warn("CouponService: find coupon by coupon name ");
            throw new EntityNotFoundRuntimeException("We can not find Coupon by  name"); });

        return mapper.couponEntityToCoupon(couponEntity);
    }

    @Override
    public List<Coupon> findAll() {
        log.info("CouponService: find all coupons");
        List<CouponEntity> result = couponRepository.findAll();

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::couponEntityToCoupon)
                .collect(Collectors.toList());
    }


}
