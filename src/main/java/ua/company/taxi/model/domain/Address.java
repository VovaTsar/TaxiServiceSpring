package ua.company.taxi.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.company.taxi.model.entity.Street;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private Long id;

    private Street initialPlace;

    private Street destinationPlace;

    private Long price;

    private Long time;

}
