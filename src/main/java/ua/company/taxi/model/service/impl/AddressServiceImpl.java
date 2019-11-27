package ua.company.taxi.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.company.taxi.model.exception.UncorrectInputDataRuntimeExeption;
import ua.company.taxi.model.service.AddressService;
import ua.company.taxi.model.domain.Address;
import ua.company.taxi.model.entity.Street;
import ua.company.taxi.model.exception.AddressEntityNotFoundRuntimeException;
import ua.company.taxi.model.mapper.AddressMapper;
import ua.company.taxi.model.repository.AddressRepository;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    @Override
    public Long findLongTime(Street initialPlace, Street destinationPlace) {
        if (Objects.isNull(initialPlace)||Objects.isNull(destinationPlace)){
            log.error("AddressServiceImpl:findLongTime");
            throw new UncorrectInputDataRuntimeExeption("initialPlace or destinationPlace is empty");
        }
        log.info("AddressServiceImpl:findLongTime");
        return findTime(initialPlace, destinationPlace).getTime();
    }

    @Override
    public Address findTime(Street initialPlace, Street destinationPlace) {
        log.info("AddressServiceImpl:findTime");
        return addressMapper.addressEntityToAddress(addressRepository
                .findAllByDestinationPlaceAndInitialPlace(initialPlace, destinationPlace)
                .orElseThrow(() ->(new AddressEntityNotFoundRuntimeException("Address Entity Not Found!"))));
    }


    @Override
    public Address findById(Long id) {
        if (id<0){
            log.error("AddressServiceImpl:findById");
            throw new UncorrectInputDataRuntimeExeption("Id must be positive");
        }
        log.info("AddressServiceImpl:findById");
        return addressMapper.addressEntityToAddress(addressRepository
                .findById(id)
                .orElseThrow(() -> new AddressEntityNotFoundRuntimeException("Address Entity Not Found!")));
    }


}
