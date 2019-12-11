package ua.company.taxi.model.mapper;

import org.springframework.stereotype.Component;
import ua.company.taxi.model.domain.Discount;
import ua.company.taxi.model.entity.DiscountEntity;

@Component
public class DiscountMapper {

    public Discount discountEntityToDiscount(DiscountEntity discountEntity) {
        if (discountEntity == null) {
            return null;
        }

        return Discount.builder()
                .id(discountEntity.getId())
                .discount(discountEntity.getDiscount())
                .minSpentValue(discountEntity.getMinSpentValue())
                .build();
    }

    public DiscountEntity discountToDiscountEntity(Discount discount) {
        if (discount == null) {
            return null;
        }

        return DiscountEntity.builder()
                .id(discount.getId())
                .discount(discount.getDiscount())
                .minSpentValue(discount.getMinSpentValue())
                .build();
    }
}
