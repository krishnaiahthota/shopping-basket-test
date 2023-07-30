package com.delta.capita.shoppingbasket.service;

import com.delta.capita.shoppingbasket.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.delta.capita.shoppingbasket.constants.ItemNameConstants.LIME;
import static com.delta.capita.shoppingbasket.constants.ItemNameConstants.MELON;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BuyThreeForPriceOfTwoPriceServiceTest {

    private BuyThreeForPriceOfTwoPriceService buyThreeForPriceOfTwoPriceService;
    @BeforeAll
    public void setup() {
        buyThreeForPriceOfTwoPriceService = new BuyThreeForPriceOfTwoPriceService();
    }

    /**
     * Given a list of items.
     * <p>
     * When calculating the price after discount.
     * <p>
     * Then returns the final price.
     */
    @Test
    void calculatePriceWithoutOfferApplicable() {
        //Given
        List<Item> items = Arrays.asList(new Item(LIME, 0.15), new Item(LIME, 0.15));
        //When
        BigDecimal price = buyThreeForPriceOfTwoPriceService.calculatePrice(items);

        //Then
        Assertions.assertEquals(BigDecimal.valueOf(0.30), price);
    }

    /**
     * Given a list of items.
     * <p>
     * When calculating the price after discount.
     * <p>
     * Then returns the final price.
     */
    @Test
    void calculatePriceWithOffer() {
        //Given
        List<Item> items = Arrays.asList(new Item(LIME, 0.15), new Item(LIME, 0.15), new Item(LIME, 0.15));
        //When
        BigDecimal price = buyThreeForPriceOfTwoPriceService.calculatePrice(items);

        //Then
        Assertions.assertEquals(BigDecimal.valueOf(0.30), price);
    }
}
