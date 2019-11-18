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
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_car")
    private Long idCar;

    @Column(name = "brand", nullable = false, length = 20)
    private String brand;

    @Column(name = "car_number", nullable = false, unique = true, length = 20)
    private String carNumber;

    @Column(name = "car_type", nullable = false, length = 20)
    private String carType;

    @Column(name = "color", nullable = false, length = 20)
    private String color;
}
