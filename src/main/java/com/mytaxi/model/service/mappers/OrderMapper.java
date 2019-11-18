package com.mytaxi.model.service.mappers;

import com.mytaxi.model.domain.Order;
import com.mytaxi.model.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OrderMapper {
    Order orderEntityToOrder(OrderEntity orderEntity);
    OrderEntity orderToOrderEntity(Order order);
}
