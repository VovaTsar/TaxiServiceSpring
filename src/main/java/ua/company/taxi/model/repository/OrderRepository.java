package ua.company.taxi.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.company.taxi.model.entity.CarEntity;
import ua.company.taxi.model.entity.ClientEntity;
import ua.company.taxi.model.entity.OrderEntity;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByClientEntityId(Long id);

    Long countAllByCarEntity(CarEntity carEntity);

    Long countAllByClientEntity(ClientEntity clientEntity);


}
