package com.delta.capita.shoppingbasket.service;


import com.delta.capita.shoppingbasket.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.delta.capita.shoppingbasket.constants.ItemNameConstants.MELON;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BuyOneGetOnePriceServiceTest {

    private BuyOneGetOnePriceService buyOneGetOnePriceService;
    @BeforeAll
    public void setup() {
        buyOneGetOnePriceService = new BuyOneGetOnePriceService();
    }

    /**
     * Given a list of items.
     * <p>
     * When calculating the price after discount.
     * <p>
     * Then returns the final price.
     */
    @Test
    void calculatePriceTest() {
        //Given
        List<Item> items = Arrays.asList(new Item(MELON, 0.50), new Item(MELON, 0.50));
        //When
        BigDecimal price = buyOneGetOnePriceService.calculatePrice(items);

        //Then
        Assertions.assertEquals(BigDecimal.valueOf(0.50), price);
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
        List<Item> items = Arrays.asList(new Item(MELON, 0.50));
        //When
        BigDecimal price = buyOneGetOnePriceService.calculatePrice(items);

        //Then
        Assertions.assertEquals(BigDecimal.valueOf(0.50), price);
    }
}
