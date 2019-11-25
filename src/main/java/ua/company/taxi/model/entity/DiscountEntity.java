package ua.company.taxi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "discounts")
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_discounts", unique = true, nullable = false)
    private Long id;

    @Column(name = "discount", nullable = false)
    private Integer discount;

    @Column(name = "min_spent_value", nullable = false)
    private Long minSpentValue;
}
