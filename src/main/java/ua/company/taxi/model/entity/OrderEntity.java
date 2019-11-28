package ua.company.taxi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
@AllArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_order", unique = true, nullable = false)
    private Long id;

    @JoinColumn(name = "carEntity", referencedColumnName = "id_car")
    @ManyToOne(optional = false)
    private CarEntity carEntity;

    @JoinColumn(name = "clientEntity", referencedColumnName = "id_client")
    @ManyToOne(optional = false)
    private ClientEntity clientEntity;


    @JoinColumn(name = "addressEntity", referencedColumnName = "id_address")
    @ManyToOne(optional = false)
    private AddressEntity addressEntity;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "wait_time", nullable = false)
    private Long waitTime;
}
