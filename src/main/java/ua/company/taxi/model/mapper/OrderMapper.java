package ua.company.taxi.model.mapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.company.taxi.model.domain.Order;
import ua.company.taxi.model.entity.OrderEntity;

@Component
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderMapper {
    private CarMapper carMapper;
    private ClientMapper clientMapper;
    private AddressMapper addressMapper;

    public Order orderEntityToOrder(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        return Order.builder()
                .id(orderEntity.getId())
                .car(carMapper.carEntityToCar(orderEntity.getCarEntity()))
                .client(clientMapper.clientEntityToClient(orderEntity.getClientEntity()))
                .address(addressMapper.addressEntityToAddress(orderEntity.getAddressEntity()))
                .price(orderEntity.getPrice())
                .waitTime(orderEntity.getWaitTime())
                .build();
    }

    public OrderEntity orderToOrderEntity(Order order) {
        if (order == null) {
            return null;
        }

        return OrderEntity.builder()
                .id(order.getId())
                .carEntity(carMapper.carToCarEntity(order.getCar()))
                .clientEntity(clientMapper.clientToClientEntity(order.getClient()))
                .addressEntity(addressMapper.addressToAddressEntity(order.getAddress()))
                .price(order.getPrice())
                .waitTime(order.getWaitTime())
                .build();
    }
}
