package com.mytaxi.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
@Data
@Builder
@AllArgsConstructor
public class Coupon {

    private Long idCoupon;
    @Pattern(regexp = "(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}")
    @NotEmpty
    private String couponName;
    @Min(10)
    @Max(150)
    private int discount;
}
