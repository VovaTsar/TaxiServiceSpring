package ua.company.taxi.model.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.company.taxi.model.entity.ClientEntity;
import ua.company.taxi.model.entity.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByLogin(String login);

    List<ClientEntity> findByRole(Role role);

    @Modifying
    @Transactional
    @Query("UPDATE ClientEntity SET totalSpentValue = totalSpentValue + :addValue WHERE id = :id")
    void updateSpentValue(Long id, Long addValue);

}
