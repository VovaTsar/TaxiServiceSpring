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
@Table(name = "addresses")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_address", unique = true, nullable = false)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "initial_place", nullable = false)
    private Street initialPlace;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "destination_place", nullable = false)
    private Street destinationPlace;

    @Column(name = "price", nullable = false)
    private Long price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressEntity")
    private Collection<OrderEntity> orderCollection;

    @Column(name = "time", nullable = false)
    private Long time;
}
