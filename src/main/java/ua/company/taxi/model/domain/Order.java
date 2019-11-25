package ua.company.taxi.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.company.taxi.model.entity.CarType;
import ua.company.taxi.model.entity.Street;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    private Long id;

    private Car car;

    private Client client;

    private Address address;

    private Long price;

    private Long waitTime;

    private String carMake;

    private Long time;

    private Street initPlace;

    private Street destPlace;

    private CarType carType;

}
