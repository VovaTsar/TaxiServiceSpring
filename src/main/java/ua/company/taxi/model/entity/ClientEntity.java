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
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client", unique = true, nullable = false)
    private Long id;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientEntity")
    private Collection<OrderEntity> orderCollection;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "total_spent_value", nullable = false)
    private Long totalSpentValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;


}
