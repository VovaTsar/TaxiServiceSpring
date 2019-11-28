package ua.company.taxi.model.service;

import org.springframework.stereotype.Service;
import ua.company.taxi.model.domain.Address;
import ua.company.taxi.model.entity.Street;

@Service
public interface AddressService {

    Long findLongTime(Street initialPlace, Street destinationPlace);

    Address findAllByDestinationPlaceAndInitialPlace(Street initialPlace, Street destinationPlace);

    Address findById(Long id);

}
