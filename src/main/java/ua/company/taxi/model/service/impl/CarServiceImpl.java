package ua.company.taxi.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.company.taxi.model.domain.Car;
import ua.company.taxi.model.entity.CarEntity;
import ua.company.taxi.model.entity.CarType;
import ua.company.taxi.model.exception.CarEntityNotFoundRuntimeException;
import ua.company.taxi.model.exception.UnCorrectInputDataRuntimeException;
import ua.company.taxi.model.mapper.CarMapper;
import ua.company.taxi.model.repository.CarRepository;
import ua.company.taxi.model.service.CarService;
import ua.company.taxi.model.entity.Street;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;


    @Override
    public List<Car> getAvailableType(CarType type, Street street) {
        if (Objects.isNull(type)||Objects.isNull(street)){
            log.error("CarServiceImpl:getAvailableType");
            throw new UnCorrectInputDataRuntimeException("type or street is empty");
        }
        List<CarEntity> carEntities = carRepository.findAllByTypeAndPlace(type, street);

        return carEntities.isEmpty() ?
                Collections.emptyList() : carEntities.stream()
                .map(carMapper::carEntityToCar)
                .collect(Collectors.toList());
    }


    @Override
    public Car getCarById(Long carId) {
        if (carId<0){
            log.error("CarServiceImpl:getCarById");
            throw new UnCorrectInputDataRuntimeException("Id must be positive");
        }
        return carMapper.carEntityToCar(carRepository
                .findById(carId)
                .orElseThrow(() -> new CarEntityNotFoundRuntimeException("Car Entity Not Found")));
    }
}
