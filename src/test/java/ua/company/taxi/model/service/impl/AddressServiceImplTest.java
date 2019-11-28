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

import static org.mockito.Mockito.reset;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AddressServiceImpl.class})
public class AddressServiceImplTest {
//    private Long id;
//
//    private Street initialPlace;
//
//    private Street destinationPlace;
//
//    private Long price;
//
//    private Long time;
    private static final Address ADDRESS = new Address(1L, Street.Kreschatyk,Street.Polytech,100L,30L);
    private static final AddressEntity ADDRESS_ENTITY = new AddressEntity();

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
    public void  findLongTimeShouldThrowUnCorrectInputDataRuntimeExceptionWithNegativeId() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("InitialPlace or destinationPlace is empty");

        service.findLongTime(null,null);
    }

    @Test
    public void findByDestinationPlaceAndInitialPlaceShouldThrowAddressEntityNotFoundRuntimeExceptionWithParamsAreEmpty() {
        exception.expect(AddressEntityNotFoundRuntimeException.class);
        exception.expectMessage("Address Entity Not Found!");

        service.findAllByDestinationPlaceAndInitialPlace(null, null);
    }
    @Test
    public void  findByIdShouldThrowUnCorrectInputDataRuntimeExceptionWithNegativeId() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("Id must be positive");

        service.findById(-1L);
    }
    @Test
    public void  findByIdShouldThrowAddressEntityNotFoundRuntimeExceptionWithEntityNotPresent() {
        exception.expect(AddressEntityNotFoundRuntimeException.class);
        exception.expectMessage("Address Entity Not Found!");

        service.findById(2L);
    }
}