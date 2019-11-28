package ua.company.taxi.model.service;

import org.springframework.stereotype.Service;
import ua.company.taxi.model.domain.Order;

@Service
public interface OrderService {

    void addOrder(Order order);

    Integer getNumRides(Long id);

}
