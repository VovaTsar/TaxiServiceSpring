package ua.company.taxi.model.mapper;

import org.junit.Test;
import ua.company.taxi.model.domain.Address;
import ua.company.taxi.model.entity.AddressEntity;
import ua.company.taxi.model.entity.Street;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AddressMapperTest {

    private static final AddressEntity ADDRESS_ENTITY = getAddressEntity();

    private static final Address ADDRESS = getAddress();

    private final AddressMapper addressMapper = new AddressMapper();


    @Test
    public void shouldMapAddressEntityToAddress() {
        Address actual = addressMapper.addressEntityToAddress(ADDRESS_ENTITY);

        assertThat(actual.getId(), is(ADDRESS.getId()));
        assertThat(actual.getInitialPlace(), is(ADDRESS.getInitialPlace()));
        assertThat(actual.getDestinationPlace(), is(ADDRESS.getDestinationPlace()));
        assertThat(actual.getPrice(), is(ADDRESS.getPrice()));
        assertThat(actual.getTime(), is(ADDRESS.getTime()));
    }

    @Test
    public void shouldMapAddressToAddressEntity() {
        AddressEntity actual = addressMapper.addressToAddressEntity(ADDRESS);

        assertThat(actual.getId(), is(ADDRESS_ENTITY.getId()));
        assertThat(actual.getInitialPlace(), is(ADDRESS_ENTITY.getInitialPlace()));
        assertThat(actual.getDestinationPlace(), is(ADDRESS_ENTITY.getDestinationPlace()));
        assertThat(actual.getPrice(), is(ADDRESS_ENTITY.getPrice()));
        assertThat(actual.getTime(), is(ADDRESS_ENTITY.getTime()));
    }

    @Test
    public void mapAddressToAddressEntityShouldReturnNull() {
        AddressEntity actual = addressMapper.addressToAddressEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapAddressEntityToAddressShouldReturnNull() {
        Address actual = addressMapper.addressEntityToAddress(null);
        assertThat(actual, is(nullValue()));
    }

    private static AddressEntity getAddressEntity() {
        return new AddressEntity(1L, Street.Kreschatyk, Street.Polytech, 100L, null, 30L);
    }

    private static Address getAddress() {
        return Address.builder()
                .id(1L)
                .initialPlace(Street.Kreschatyk)
                .destinationPlace(Street.Polytech)
                .price(100L)
                .time(30L)
                .build();
    }
}
