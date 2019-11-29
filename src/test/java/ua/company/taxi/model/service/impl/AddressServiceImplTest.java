package ua.company.taxi.model.service.impl;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.company.taxi.model.domain.Address;
import ua.company.taxi.model.entity.AddressEntity;
import ua.company.taxi.model.entity.Street;
import ua.company.taxi.model.exception.AddressEntityNotFoundRuntimeException;
import ua.company.taxi.model.exception.UnCorrectInputDataRuntimeException;
import ua.company.taxi.model.mapper.AddressMapper;
import ua.company.taxi.model.repository.AddressRepository;
import ua.company.taxi.model.service.AddressService;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AddressServiceImpl.class})
public class AddressServiceImplTest {

    private static final Address ADDRESS = new Address(1L, Street.Kreschatyk, Street.Polytech, 100L, 30L);

    private static final AddressEntity ENTITY = new AddressEntity();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private AddressRepository repository;

    @MockBean
    private AddressMapper mapper;

    @Autowired
    private AddressService service;

    @After
    public void resetMock() {
        reset(repository, mapper);
    }


    @Test
    public void findByIdShouldReturnAddressById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(ENTITY));
        when(mapper.addressEntityToAddress(any(AddressEntity.class))).thenReturn(ADDRESS);
        Address actual = service.findById(1L);

        verify(repository).findById(anyLong());
        verify(mapper).addressEntityToAddress(any(AddressEntity.class));

        assertThat(actual, equalTo(ADDRESS));
    }

    @Test
    public void findAllByDestinationPlaceAndInitialPlaceShouldReturnAddressByTwoParam() {
        when(repository.findAllByDestinationPlaceAndInitialPlace(any(), any())).thenReturn(Optional.of(ENTITY));
        when(mapper.addressEntityToAddress(any(AddressEntity.class))).thenReturn(ADDRESS);
        Address actual = service.findAllByDestinationPlaceAndInitialPlace(Street.Kreschatyk, Street.Polytech);

        verify(repository).findAllByDestinationPlaceAndInitialPlace(any(), any());
        verify(mapper).addressEntityToAddress(any(AddressEntity.class));

        assertThat(actual, equalTo(ADDRESS));
    }

    @Test
    public void findTimeOfOrderShouldReturnTimeByTwoStringParam() {
        when(repository.findAllByDestinationPlaceAndInitialPlace(any(), any())).thenReturn(Optional.of(ENTITY));
        when(mapper.addressEntityToAddress(any(AddressEntity.class))).thenReturn(ADDRESS);
        Long actual = service.findAllByDestinationPlaceAndInitialPlace(Street.Kreschatyk, Street.Polytech).getTime();

        verify(repository).findAllByDestinationPlaceAndInitialPlace(any(), any());
        verify(mapper).addressEntityToAddress(any(AddressEntity.class));


        assertThat(actual, equalTo(30L));
    }

    @Test
    public void findTimeOfOrderShouldThrowUnCorrectInputDataRuntimeExceptionWithNegativeId() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("InitialPlace or destinationPlace is empty");

        service.findTimeOfOrder(null, null);
    }

    @Test
    public void findByDestinationPlaceAndInitialPlaceShouldThrowAddressEntityNotFoundRuntimeExceptionWithParamsAreEmpty() {
        exception.expect(AddressEntityNotFoundRuntimeException.class);
        exception.expectMessage("Address Entity Not Found!");

        service.findAllByDestinationPlaceAndInitialPlace(null, null);
    }

    @Test
    public void findByIdShouldThrowUnCorrectInputDataRuntimeExceptionWithNegativeId() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("Id must be positive");

        service.findById(-1L);
    }

    @Test
    public void findByIdShouldThrowAddressEntityNotFoundRuntimeExceptionWithNoEntity() {
        exception.expect(AddressEntityNotFoundRuntimeException.class);
        exception.expectMessage("Address Entity Not Found!");

        service.findById(2L);
    }
}
