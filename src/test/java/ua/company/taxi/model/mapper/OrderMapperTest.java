package ua.company.taxi.model.mapper;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.company.taxi.model.domain.Address;
import ua.company.taxi.model.domain.Car;
import ua.company.taxi.model.domain.Client;
import ua.company.taxi.model.domain.Order;
import ua.company.taxi.model.entity.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {OrderMapper.class})
public class OrderMapperTest {

    private static final CarEntity CAR_ENTITY = getCarEntity();

    private static final Car CAR = getCar();

    private static final ClientEntity CLIENT_ENTITY = getClientEntity();

    private static final Client CLIENT = getClient();

    private static final AddressEntity ADDRESS_ENTITY = getAddressEntity();

    private static final Address ADDRESS = getAddress();

    private static final OrderEntity ORDER_ENTITY = getOrderEntity();

    private static final Order ORDER = getOrder();


    @MockBean
    private CarMapper carMapper;

    @MockBean
    private ClientMapper clientMapper;

    @MockBean
    private AddressMapper addressMapper;

    @Autowired
    private OrderMapper orderMapper;

    @After
    public void resetMock() {
        reset(carMapper, clientMapper, addressMapper);
    }


    @Test
    public void shouldMapOrderEntityToOrder() {
        when(carMapper.carEntityToCar(any(CarEntity.class))).thenReturn(CAR);
        when(clientMapper.clientEntityToClient(any(ClientEntity.class))).thenReturn(CLIENT);
        when(addressMapper.addressEntityToAddress(any(AddressEntity.class))).thenReturn(ADDRESS);

        Order actual = orderMapper.orderEntityToOrder(ORDER_ENTITY);

        assertThat(actual.getId(), is(ORDER.getId()));
        assertThat(actual.getCar(), is(ORDER.getCar()));
        assertThat(actual.getClient(), is(ORDER.getClient()));
        assertThat(actual.getAddress(), is(ORDER.getAddress()));
        assertThat(actual.getPrice(), is(ORDER.getPrice()));
        assertThat(actual.getWaitTime(), is(ORDER.getWaitTime()));

    }

    @Test
    public void shouldMapOrderToOrderEntity() {
        when(carMapper.carToCarEntity(any(Car.class))).thenReturn(CAR_ENTITY);
        when(clientMapper.clientToClientEntity(any(Client.class))).thenReturn(CLIENT_ENTITY);
        when(addressMapper.addressToAddressEntity(any(Address.class))).thenReturn(ADDRESS_ENTITY);


        OrderEntity actual = orderMapper.orderToOrderEntity(ORDER);

        assertThat(actual.getId(), is(ORDER_ENTITY.getId()));
        assertThat(actual.getCarEntity(), is(ORDER_ENTITY.getCarEntity()));
        assertThat(actual.getClientEntity(), is(ORDER_ENTITY.getClientEntity()));
        assertThat(actual.getAddressEntity(), is(ORDER_ENTITY.getAddressEntity()));
        assertThat(actual.getPrice(), is(ORDER_ENTITY.getPrice()));
        assertThat(actual.getWaitTime(), is(ORDER_ENTITY.getWaitTime()));
    }

    @Test
    public void mapGoodToGoodEntityShouldReturnNull() {
        OrderEntity actual = orderMapper.orderToOrderEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapGoodEntityToGoodShouldReturnNull() {
        Order actual = orderMapper.orderEntityToOrder(null);

        assertThat(actual, is(nullValue()));
    }

    private static CarEntity getCarEntity() {
        return new CarEntity(1L, "Mercedes", Street.Polytech, null, CarType.MINIVAN);
    }

    private static Car getCar() {
        return Car.builder()
                .id(1L)
                .carMake("Mercedes")
                .place(Street.Polytech)
                .carType(CarType.MINIVAN)
                .build();
    }

    private static ClientEntity getClientEntity() {
        return new ClientEntity(1L, "Vova777", null, "cerber", 100L, Role.ROLE_USER);
    }

    private static Client getClient() {
        return Client.builder()
                .id(1L)
                .login("Vova777")
                .password("cerber")
                .totalSpentValue(100L)
                .role(Role.ROLE_USER)
                .build();
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

    private static OrderEntity getOrderEntity() {
        return new OrderEntity(1L, CAR_ENTITY, CLIENT_ENTITY, ADDRESS_ENTITY, 100L, 30L);
    }

    private static Order getOrder() {
        return Order.builder()
                .id(1L)
                .car(CAR)
                .client(CLIENT)
                .address(ADDRESS)
                .price(100L)
                .waitTime(30L)
                .build();
    }
}
