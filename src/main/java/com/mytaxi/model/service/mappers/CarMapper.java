package com.mytaxi.model.service.mappers;

import com.mytaxi.model.domain.Car;
import com.mytaxi.model.entities.CarEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CarMapper {
    Car carEntityToCar (CarEntity carEntity);
    CarEntity carToCarEntity(Car car);
}
