package ua.company.taxi.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discount {

    private Long id;
    @Min(1)
    @Max(50)
    private Integer discount;

    private Long minSpentValue;

}
