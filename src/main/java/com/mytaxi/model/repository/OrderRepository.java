package com.mytaxi.model.repository;

import com.mytaxi.model.domain.enums.OrderStatus;
import com.mytaxi.model.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
   Optional<OrderEntity> findByIdOrderAndOrderStatus(Long idOrder, OrderStatus orderStatus);
}
