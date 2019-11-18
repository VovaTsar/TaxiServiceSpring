package com.mytaxi.model.service.mappers;

import com.mytaxi.model.domain.Address;
import com.mytaxi.model.entities.AddressEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AddressMapper {
    Address addressEntitytoAddress(AddressEntity addressEntity);
    AddressEntity addressToAddressEntity(Address address);
}
