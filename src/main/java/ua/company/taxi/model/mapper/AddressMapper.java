package ua.company.taxi.model.mapper;

import org.springframework.stereotype.Component;
import ua.company.taxi.model.domain.Address;
import ua.company.taxi.model.entity.AddressEntity;

@Component
public class AddressMapper {

    public Address addressEntityToAddress(AddressEntity addressEntity) {
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