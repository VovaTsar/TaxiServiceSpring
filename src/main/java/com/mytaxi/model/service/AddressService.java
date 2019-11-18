package com.mytaxi.model.service;

import com.mytaxi.model.domain.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAllAddresses();

    Address findAddressById(Long idAddress);

}
