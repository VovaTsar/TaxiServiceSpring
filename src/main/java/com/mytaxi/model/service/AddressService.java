package com.mytaxi.model.service;

import com.mytaxi.model.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> findAllAddresses();

    Optional<Address> findAddressById(Long idAddress);

}
