package ua.company.taxi.model.mapper;

import org.junit.Test;
import ua.company.taxi.model.domain.Car;
import ua.company.taxi.model.entity.CarEntity;
import ua.company.taxi.model.entity.CarType;
import ua.company.taxi.model.entity.Street;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CarMapperTest {

    private static final CarEntity CAR_ENTITY = getCarEntity();

    private static final Car CAR = getCar();

    private final CarMapper carMapper = new CarMapper();


    @Test
    public void shouldMapCarEntityToCar() {
        Car actual = carMapper.carEntityToCar(CAR_ENTITY);

        assertThat(actual.getId(), is(CAR.getId()));
        assertThat(actual.getCarMake(), is(CAR.getCarMake()));
        assertThat(actual.getPlace(), is(CAR.getPlace()));
        assertThat(actual.getCarType(), is(CAR.getCarType()));

    }

    @Test
    public void shouldMapCarToCarEntity() {
        CarEntity actual = carMapper.carToCarEntity(CAR);

        assertThat(actual.getId(), is(CAR_ENTITY.getId()));
        assertThat(actual.getMake(), is(CAR_ENTITY.getMake()));
        assertThat(actual.getPlace(), is(CAR_ENTITY.getPlace()));
        assertThat(actual.getType(), is(CAR_ENTITY.getType()));

    }

    @Test
    public void mapCarToCarEntityShouldReturnNull() {
        CarEntity actual = carMapper.carToCarEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapCarEntityToCarShouldReturnNull() {
        Car actual = carMapper.carEntityToCar(null);
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

}
