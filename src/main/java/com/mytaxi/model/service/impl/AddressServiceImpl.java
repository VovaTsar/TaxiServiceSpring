package com.mytaxi.model.service.impl;

import com.mytaxi.model.domain.Address;
import com.mytaxi.model.entities.AddressEntity;
import com.mytaxi.model.repository.AddressRepository;
import com.mytaxi.model.service.AddressService;
import com.mytaxi.model.service.mappers.AddressMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper mapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Address> findAllAddresses() {
        log.info("get all addresses");
        List<AddressEntity> result = addressRepository.findAll();
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::addressEntitytoAddress)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Address> findAddressById(Long idAddress) {
        return Optional.empty();
    }
}
