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
import ua.company.taxi.model.domain.Order;
import ua.company.taxi.model.entity.CarType;
import ua.company.taxi.model.entity.OrderEntity;
import ua.company.taxi.model.exception.UnCorrectInputDataRuntimeException;
import ua.company.taxi.model.mapper.OrderMapper;
import ua.company.taxi.model.repository.OrderRepository;
import ua.company.taxi.model.service.OrderService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {OrderServiceImpl.class})
public class OrderServiceImplTest {

    private static final Integer EXPECTED = 2;

    private static final Order ORDER = Order.builder()
            .id(1L)
            .carType(CarType.MINIVAN)
            .build();

    private static final OrderEntity ENTITY = OrderEntity.builder().build();

    private static final List<OrderEntity> ORDER_ENTITIES = Arrays.asList(ENTITY, ENTITY);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private OrderRepository repository;

    @MockBean
    private OrderMapper mapper;

    @Autowired
    private OrderService service;

    @After
    public void resetMock() {
        reset(repository, mapper);
    }

    @Test
    public void addOrderShouldThrowUnCorrectInputDataRuntimeExceptionWithNoOrder() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("Order is empty");

        service.addOrder(null);
    }

    @Test
    public void addOrderShouldSaveOrder() {
        when(repository.save(any(OrderEntity.class))).thenReturn(ENTITY);
        when(mapper.orderToOrderEntity(any(Order.class))).thenReturn(ENTITY);
        Order actual = Order.builder()
                .id(1L)
                .carType(CarType.MINIVAN)
                .build();
        service.addOrder(actual);

        assertThat(actual, equalTo(ORDER));

    }

    @Test
    public void getNumRidesShouldThrowUnCorrectInputDataRuntimeExceptionWithNegativeId() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("Id must be positive");

        service.getNumRides(-100L);
    }

    @Test
    public void getNumRidesShouldReturnNumberOfRides() {
        when(repository.findAllByClientEntityId(anyLong())).thenReturn(ORDER_ENTITIES);
        Integer actual = service.getNumRides(1L);

        assertThat(actual, equalTo(EXPECTED));
    }

}
