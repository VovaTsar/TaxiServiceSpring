package com.mytaxi.model.entities;

import com.mytaxi.model.domain.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_order")
    private Long idOrder;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status", nullable = false, length = 20)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @Column(name = "discount", nullable = false, length = 20)
    private UserEntity  user;

    @ManyToOne
    @JoinColumn(name = "id_address_arrive")
    private AddressEntity addressArrive;

    @ManyToOne
    @JoinColumn(name = "id_address_departure")
    private AddressEntity addressDeparture;

    @ManyToOne
    @JoinColumn(name = "id_coupon")
    private CouponEntity coupon;

    @Column(name = "cost", nullable = false, length = 20)
    private int cost;

    @Column(name = "cost_with_discount", nullable = false, length = 20)
    private int costWithDiscount;
}
