package com.mytaxi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "coupons")
public class CouponEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_coupon")
    private Long idCoupon;

    @Column(name = "coupon_name", nullable = false, unique = true, length = 20)
    private String couponName;

    @Column(name = "discount", nullable = false, length = 20)
    private int discount;
}
