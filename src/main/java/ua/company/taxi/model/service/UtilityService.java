package ua.company.taxi.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.company.taxi.model.domain.Car;
import ua.company.taxi.model.domain.Client;
import ua.company.taxi.model.domain.Order;

@Service
public interface UtilityService {

    Long countPrice(Integer discount, Long time);

    Page<Order> buildPageOrders(Pageable pageable, Client client);

    Page<Car> buildPageCars(Pageable pageable);

    Page<Client> buildPageClients(Pageable pageable);

}
