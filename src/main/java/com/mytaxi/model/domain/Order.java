package com.mytaxi.model.domain;

import com.mytaxi.model.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
@Data
@Builder
@AllArgsConstructor
public class Order {
    private Long idOrder;
    @NotNull
    private OrderStatus orderStatus;

    @NotNull
    private User user;

    @Pattern(regexp = "(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}")
    @NotEmpty
    private Address addressArrive;

    @Pattern(regexp = "(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}")
    @NotEmpty
    private Address addressDeparture;


    private Coupon coupon;

    @Min(10)
    @Max(100000)
    @NotNull
    private int cost;

    @Min(10)
    @Max(100000)
    @NotNull
    private int costWithDiscount;
}
