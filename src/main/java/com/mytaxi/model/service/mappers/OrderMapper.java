package com.mytaxi.model.service.mappers;

import com.mytaxi.model.domain.Order;
import com.mytaxi.model.entities.OrderEntity;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    Order orderEntitytoOrder(OrderEntity orderEntity);
    OrderEntity orderToOrderEntity(Order order);
}
