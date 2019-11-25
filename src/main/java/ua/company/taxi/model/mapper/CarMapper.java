package ua.company.taxi.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.company.taxi.model.entity.CarEntity;
import ua.company.taxi.model.domain.Car;

@Component
@Slf4j
public class CarMapper {
    public Car carEntityToCar(CarEntity carEntity) {
        log.info("CarMapper:carEntityToCar");
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
        log.info("CarMapper:carToCarEntity");
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
