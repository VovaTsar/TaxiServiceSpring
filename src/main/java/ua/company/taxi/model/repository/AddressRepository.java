package ua.company.taxi.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.company.taxi.model.entity.AddressEntity;
import ua.company.taxi.model.entity.Street;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

    Optional<AddressEntity> getTimeById(Long id);

    Optional<AddressEntity> findAllByDestinationPlaceAndInitialPlace(Street initialPlace, Street destinationPlace);


}
