package ua.company.taxi.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.company.taxi.model.domain.Address;
import ua.company.taxi.model.entity.Street;
import ua.company.taxi.model.exception.AddressEntityNotFoundRuntimeException;
import ua.company.taxi.model.exception.UnCorrectInputDataRuntimeException;
import ua.company.taxi.model.mapper.AddressMapper;
import ua.company.taxi.model.repository.AddressRepository;
import ua.company.taxi.model.service.AddressService;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    @Override
    public Long findTimeOfOrder(Street initialPlace, Street destinationPlace) {
        if (Objects.isNull(initialPlace) || Objects.isNull(destinationPlace)) {
            log.warn("AddressServiceImpl:findTimeOfOrder");
            throw new UnCorrectInputDataRuntimeException("InitialPlace or destinationPlace is empty");
        }
        return findAllByDestinationPlaceAndInitialPlace(initialPlace, destinationPlace).getTime();
    }

    @Override
    public Address findAllByDestinationPlaceAndInitialPlace(Street initialPlace, Street destinationPlace) {

        return addressMapper.addressEntityToAddress(addressRepository
                .findAllByDestinationPlaceAndInitialPlace(initialPlace, destinationPlace)
                .orElseThrow(() -> (new AddressEntityNotFoundRuntimeException("Address Entity Not Found!"))));
    }


    @Override
    public Address findById(Long id) {
        if (id < 0) {
            log.warn("AddressServiceImpl:findById");
            throw new UnCorrectInputDataRuntimeException("Id must be positive");
        }
        return addressMapper.addressEntityToAddress(addressRepository
                .findById(id)
                .orElseThrow(() -> new AddressEntityNotFoundRuntimeException("Address Entity Not Found!")));
    }


}
