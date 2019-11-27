package ua.company.taxi.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.company.taxi.model.entity.AddressEntity;
import ua.company.taxi.model.domain.Address;

@Component
@Slf4j
public class AddressMapper {


    public Address addressEntityToAddress(AddressEntity addressEntity) {
        log.info("AddressMapper:addressEntityToAddress");
        if (addressEntity == null) {
            return null;
        }

        return Address.builder()
                .id(addressEntity.getId())
                .initialPlace(addressEntity.getInitialPlace())
                .destinationPlace(addressEntity.getDestinationPlace())
                .price(addressEntity.getPrice())
                .time(addressEntity.getTime())
                .build();
    }

    public AddressEntity addressToAddressEntity(Address address) {
        log.info("AddressMapper:addressToAddressEntity");
        if (address == null) {
            return null;
        }

        return AddressEntity.builder()
                .id(address.getId())
                .initialPlace(address.getInitialPlace())
                .destinationPlace(address.getDestinationPlace())
                .price(address.getPrice())
                .time(address.getTime())
                .build();
    }
}