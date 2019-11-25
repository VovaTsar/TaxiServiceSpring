package ua.company.taxi.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.company.taxi.model.entity.DiscountEntity;
import ua.company.taxi.model.domain.Discount;

@Component
@Slf4j
public class DiscountMapper {
    public Discount discountEntityToDiscount(DiscountEntity discountEntity) {
        log.info("DiscountMapper:discountEntityToDiscount");
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
        log.info("DiscountMapper:discountEntityToDiscount");
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
