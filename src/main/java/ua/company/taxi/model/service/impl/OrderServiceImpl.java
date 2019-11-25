package ua.company.taxi.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.company.taxi.model.mapper.OrderMapper;
import ua.company.taxi.model.repository.OrderRepository;
import ua.company.taxi.model.service.OrderService;
import ua.company.taxi.model.domain.Order;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    @Override
    public void addOrder(Order order) {
        log.info("OrderServiceImpl:addOrder");
        orderRepository.save(orderMapper.orderToOrderEntity(order));
    }


    @Override
    public Integer getNumRides(Long id) {
        log.info("OrderServiceImpl:getNumRides");
        return orderRepository.findAllByClientEntityId(id).size();
    }
}
