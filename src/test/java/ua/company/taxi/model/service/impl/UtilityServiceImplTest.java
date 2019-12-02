package ua.company.taxi.model.service.impl;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.company.taxi.model.domain.Car;
import ua.company.taxi.model.domain.Client;
import ua.company.taxi.model.domain.Order;
import ua.company.taxi.model.entity.*;
import ua.company.taxi.model.exception.UnCorrectInputDataRuntimeException;
import ua.company.taxi.model.repository.AddressRepository;
import ua.company.taxi.model.repository.CarRepository;
import ua.company.taxi.model.repository.ClientRepository;
import ua.company.taxi.model.repository.OrderRepository;
import ua.company.taxi.model.service.UtilityService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UtilityServiceImpl.class})
public class UtilityServiceImplTest {
    private static final ClientEntity CLIENT_ENTITY = ClientEntity.builder().login("Vova777").build();
    private static final List<ClientEntity> CLIENT_ENTITIES = Arrays.asList(CLIENT_ENTITY, CLIENT_ENTITY);

    private static final CarEntity CAR_ENTITY = CarEntity.builder().build();
    private static final List<CarEntity> CAR_ENTITIES = Arrays.asList(CAR_ENTITY, CAR_ENTITY);

    private static final OrderEntity ORDER_ENTITY = OrderEntity.builder().build();
    private static final List<OrderEntity> ORDER_ENTITIES = Arrays.asList(ORDER_ENTITY, ORDER_ENTITY);

    private static final AddressEntity ADDRESS_ENTITY = AddressEntity.builder().build();

    private static final Long COUNT = 2L;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private UtilityService service;

    @After
    public void resetMock() {
        reset(addressRepository, carRepository, orderRepository, clientRepository);
    }


    @Test
    public void countPriceShouldThrowUnCorrectInputDataRuntimeExceptionWithNegativeDiscountAndTime() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("Discount or time must be positive");

        service.countPrice(-100, -30L);
    }

    @Test
    public void countPriceShouldReturnCorrectPrice() {
        Long expected = 60L;
        Long actual = service.countPrice(20, 30L);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void buildPageClientsShouldReturnCorrectPageIfClientsPresent() {
        when(clientRepository.findByRole(Role.ROLE_USER)).thenReturn(CLIENT_ENTITIES);
        when(orderRepository.countAllByClientEntity(any(ClientEntity.class))).thenReturn(COUNT);


        final Pageable pageable = mockPageable();
        final Page<Client> pageClient = service.buildPageClients(pageable);

        assertThat(pageClient.getTotalElements(), is(2L));
        assertThat(pageClient.getTotalPages(), is(1));
        final List<Client> clients = pageClient.getContent();
        assertThat(clients, hasSize(1));
    }

    @Test
    public void buildPageCarsShouldReturnCorrectPageIfCarsPresent() {
        when(carRepository.findAll()).thenReturn(CAR_ENTITIES);
        when(orderRepository.countAllByCarEntity(any(CarEntity.class))).thenReturn(COUNT);

        final Pageable pageable = mockPageable();
        final Page<Car> pageCar = service.buildPageCars(pageable);

        assertThat(pageCar.getTotalElements(), is(2L));
        assertThat(pageCar.getTotalPages(), is(1));
        final List<Car> cars = pageCar.getContent();
        assertThat(cars, hasSize(1));
    }

    @Test
    public void buildPageOrdersShouldReturnCorrectPageIfOrdersPresent() {
//        when(orderRepository.findAllByClientEntityId(anyLong())).thenReturn(ORDER_ENTITIES);
//        when(carRepository.findById(anyLong())).thenReturn(Optional.ofNullable(CAR_ENTITY));
//        when(addressRepository.findById(anyLong())).thenReturn(Optional.ofNullable(ADDRESS_ENTITY));
//
//        final Pageable pageable = mockPageable();
//        final Client client = Client.builder().build();
//        final Page<Order> pageOrder = service.buildPageOrders(pageable, client);
//
//        assertThat(pageOrder.getTotalElements(), is(2L));
//        assertThat(pageOrder.getTotalPages(), is(1));
//        final List<Order> orders = pageOrder.getContent();
//        assertThat(orders, hasSize(1));
    }


    private Pageable mockPageable() {
        final Pageable pageable = Mockito.mock(Pageable.class);
        when(pageable.getPageNumber()).thenReturn(1);
        when(pageable.getPageSize()).thenReturn(1);
        return pageable;
    }

}
