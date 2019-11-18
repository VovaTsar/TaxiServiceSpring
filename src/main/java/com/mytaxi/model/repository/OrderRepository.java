package com.mytaxi.model.repository;

import com.mytaxi.model.domain.enums.OrderStatus;
import com.mytaxi.model.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findByIdOrderAndOrderStatus(Long idOrder, OrderStatus orderStatus);
}
