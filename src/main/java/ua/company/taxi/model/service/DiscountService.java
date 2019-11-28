package ua.company.taxi.model.service;

import org.springframework.stereotype.Service;
import ua.company.taxi.model.domain.Client;

@Service
public interface DiscountService {

    Integer getClientDiscount(Client client);

}
