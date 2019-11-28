package ua.company.taxi.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.company.taxi.model.domain.Order;
import ua.company.taxi.model.exception.UnCorrectInputDataRuntimeException;
import ua.company.taxi.model.mapper.OrderMapper;
import ua.company.taxi.model.repository.OrderRepository;
import ua.company.taxi.model.service.OrderService;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    @Override
    public void addOrder(Order order) {
        if (Objects.isNull(order)) {
            log.error("OrderServiceImpl:addOrder");
            throw new UnCorrectInputDataRuntimeException("order is empty");
        }
        orderRepository.save(orderMapper.orderToOrderEntity(order));
    }


    @Override
    public Integer getNumRides(Long id) {
        if (id < 0) {
            log.error("OrderServiceImpl:getNumRides");
            throw new UnCorrectInputDataRuntimeException("Id must be positive");
        }

        return orderRepository.findAllByClientEntityId(id).size();
    }
}
