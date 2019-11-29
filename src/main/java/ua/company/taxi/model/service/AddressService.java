package ua.company.taxi.model.service;

import ua.company.taxi.model.domain.Address;
import ua.company.taxi.model.entity.Street;


public interface AddressService {

    Long findTimeOfOrder(Street initialPlace, Street destinationPlace);

    Address findAllByDestinationPlaceAndInitialPlace(Street initialPlace, Street destinationPlace);

    Address findById(Long id);

}
