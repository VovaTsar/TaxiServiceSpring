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
import ua.company.taxi.model.domain.Car;
import ua.company.taxi.model.entity.CarEntity;
import ua.company.taxi.model.entity.CarType;
import ua.company.taxi.model.entity.Street;
import ua.company.taxi.model.exception.CarEntityNotFoundRuntimeException;
import ua.company.taxi.model.exception.UnCorrectInputDataRuntimeException;
import ua.company.taxi.model.mapper.CarMapper;
import ua.company.taxi.model.repository.CarRepository;
import ua.company.taxi.model.service.CarService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CarServiceImpl.class})
public class CarServiceImplTest {

    private static final Car CAR = new Car(1L, "Mercedes", Street.Kreschatyk, 10L, CarType.MINIVAN);

    private static final CarEntity ENTITY = new CarEntity();

    private static final List<CarEntity> CAR_ENTITIES = Arrays.asList(ENTITY, ENTITY);

    private static final List<Car> CARS = Arrays.asList(CAR, CAR);


    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private CarRepository repository;

    @MockBean
    private CarMapper mapper;

    @Autowired
    private CarService service;

    @After
    public void resetMock() {
        reset(repository, mapper);
    }


    @Test
    public void getAvailableTypeShouldShowAllCars() {
        when(repository.findAllByTypeAndPlace(any(), any())).thenReturn(CAR_ENTITIES);
        when(mapper.carEntityToCar(any(CarEntity.class))).thenReturn(CAR);

        List<Car> actual = service.getAvailableType(CarType.MINIVAN, Street.Kreschatyk);

        verify(repository).findAllByTypeAndPlace(any(), any());
        verify(mapper, times(2)).carEntityToCar(any(CarEntity.class));

        assertThat(actual, equalTo(CARS));
    }

    @Test
    public void getAvailableTypeShouldThrowUnCorrectInputDataRuntimeExceptionWithParamsAreEmpty() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("Type or street is empty");

        service.getAvailableType(null, null);
    }

    @Test
    public void getCarByIdShouldReturnCarById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(ENTITY));
        when(mapper.carEntityToCar(any(CarEntity.class))).thenReturn(CAR);
        Car actual = service.getCarById(1L);

        verify(repository).findById(anyLong());
        verify(mapper).carEntityToCar(any(CarEntity.class));

        assertThat(actual, equalTo(CAR));
    }

    @Test
    public void getCarByIdShouldThrowUnCorrectInputDataRuntimeExceptionWithNegativeId() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("Id must be positive");

        service.getCarById(-1L);
    }

    @Test
    public void getCarByIdShouldThrowAddressEntityNotFoundRuntimeExceptionWithNoEntity() {
        exception.expect(CarEntityNotFoundRuntimeException.class);
        exception.expectMessage("Car Entity Not Found");

        service.getCarById(2L);
    }

}