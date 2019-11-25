package ua.company.taxi.model.domain;

import lombok.*;
import ua.company.taxi.model.entity.CarType;
import ua.company.taxi.model.entity.Street;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Car {

    private Long id;

    private String carMake;

    private Street place;

    private Long numRides;

    private CarType carType;

}