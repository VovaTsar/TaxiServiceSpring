package ua.company.taxi.model.service;

import ua.company.taxi.model.domain.Client;
import org.springframework.stereotype.Service;

@Service
public interface DiscountService {

    Integer getClientDiscount(Client client);

}
