package ua.company.taxi.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.company.taxi.model.entity.CarEntity;
import ua.company.taxi.model.entity.CarType;
import ua.company.taxi.model.entity.Street;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, Long> {

    List<CarEntity> findAllByTypeAndPlace(CarType type, Street place);

}
