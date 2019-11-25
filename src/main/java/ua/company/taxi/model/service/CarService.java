package ua.company.taxi.model.service;

import ua.company.taxi.model.domain.Car;
import ua.company.taxi.model.entity.CarType;
import ua.company.taxi.model.entity.Street;

import java.util.List;

public interface CarService {

    List<Car> getAvailableType(CarType type, Street street);

    Car getCarById(Long carId);

}
