package ua.company.taxi.model.mapper;

import org.junit.Test;
import ua.company.taxi.model.domain.Discount;
import ua.company.taxi.model.entity.DiscountEntity;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DiscountMapperTest {

    private static final DiscountEntity DISCOUNT_ENTITY = getDiscountEntity();

    private static final Discount DISCOUNT = getDiscount();

    private final DiscountMapper discountMapper = new DiscountMapper();


    @Test
    public void shouldMapDiscountEntityToDiscount() {
        Discount actual = discountMapper.discountEntityToDiscount(DISCOUNT_ENTITY);

        assertThat(actual.getId(), is(DISCOUNT.getId()));
        assertThat(actual.getDiscount(), is(DISCOUNT.getDiscount()));
        assertThat(actual.getMinSpentValue(), is(DISCOUNT.getMinSpentValue()));

    }

    @Test
    public void shouldMapDiscountToDiscountEntity() {
        DiscountEntity actual = discountMapper.discountToDiscountEntity(DISCOUNT);

        assertThat(actual.getId(), is(DISCOUNT_ENTITY.getId()));
        assertThat(actual.getDiscount(), is(DISCOUNT_ENTITY.getDiscount()));
        assertThat(actual.getMinSpentValue(), is(DISCOUNT_ENTITY.getMinSpentValue()));
    }

    @Test
    public void mapDiscountToDiscountEntityShouldReturnNull() {
        DiscountEntity actual = discountMapper.discountToDiscountEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapDiscountEntityToDiscountShouldReturnNull() {
        Discount actual = discountMapper.discountEntityToDiscount(null);
        assertThat(actual, is(nullValue()));
    }

    private static DiscountEntity getDiscountEntity() {
        return new DiscountEntity(1L, 10, 100L);
    }

    private static Discount getDiscount() {
        return Discount.builder()
                .id(1L)
                .discount(10)
                .minSpentValue(100L)
                .build();
    }

}
