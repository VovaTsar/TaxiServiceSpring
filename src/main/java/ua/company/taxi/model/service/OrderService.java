package ua.company.taxi.model.service;

import ua.company.taxi.model.domain.Order;

public interface OrderService {

    void addOrder(Order order);

    Integer getNumRides(Long id);

}
