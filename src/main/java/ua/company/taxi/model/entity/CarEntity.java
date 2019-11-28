package ua.company.taxi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cars")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_car", unique = true, nullable = false)
    private Long id;

    @Column(name = "make", columnDefinition = "VARCHAR(45) default 'unknown'")
    private String make;

    @Enumerated(EnumType.STRING)
    @Column(name = "place", nullable = false)
    private Street place;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carEntity")
    private Collection<OrderEntity> orderCollection;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CarType type;
}
