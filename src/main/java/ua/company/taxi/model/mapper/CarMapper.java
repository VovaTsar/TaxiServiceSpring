package ua.company.taxi.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.company.taxi.model.domain.Car;
import ua.company.taxi.model.entity.CarEntity;

@Component
@Slf4j
public class CarMapper {
    public Car carEntityToCar(CarEntity carEntity) {
        if (carEntity == null) {
            return null;
        }

        return Car.builder()
                .id(carEntity.getId())
                .carMake(carEntity.getMake())
                .place(carEntity.getPlace())
                .carType(carEntity.getType())
                .build();
    }

    public CarEntity carToCarEntity(Car car) {
        if (car == null) {
            return null;
        }

        return CarEntity.builder()
                .id(car.getId())
                .make(car.getCarMake())
                .place(car.getPlace())
                .type(car.getCarType())
                .build();
    }
}
