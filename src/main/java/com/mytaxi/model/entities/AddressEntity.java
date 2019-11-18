package com.mytaxi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "addresses")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_address")
    private Long idAddress;

    @Column(name = "house_number", nullable = false, length = 20)
    private String houseNumber;

    @Column(name = "street", nullable = false, length = 20)
    private String street;
}
