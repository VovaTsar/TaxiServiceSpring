package ua.company.taxi.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.company.taxi.model.entity.DiscountEntity;

@Repository
public interface DiscountRepository extends CrudRepository<DiscountEntity, Long> {

    @Query(value = "SELECT discount FROM discounts WHERE  min_spent_value <= :min_spent ORDER BY discount DESC LIMIT 1",
            nativeQuery = true)
    Integer getPersonalDiscount(@Param("min_spent") Long min_spent);


}
