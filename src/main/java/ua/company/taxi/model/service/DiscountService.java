package ua.company.taxi.model.service;

import ua.company.taxi.model.domain.Client;


public interface DiscountService {

    Integer getClientDiscount(Client client);

}
