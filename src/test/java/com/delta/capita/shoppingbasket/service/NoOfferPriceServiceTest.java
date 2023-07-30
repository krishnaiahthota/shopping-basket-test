package com.delta.capita.shoppingbasket.service;

import com.delta.capita.shoppingbasket.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.delta.capita.shoppingbasket.constants.ItemNameConstants.APPLE;
import static com.delta.capita.shoppingbasket.constants.ItemNameConstants.BANANA;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NoOfferPriceServiceTest {

    private NoOfferPriceService noOfferPriceService;

    @BeforeAll
    public void setup() {
        noOfferPriceService = new NoOfferPriceService();
    }

    /**
     * Given a list of items.
     * <p>
     * When calculating the price without discount.
     * <p>
     * Then returns the final price.
     */
    @Test
    void calculatePriceForBananasTest() {
        //Given
        List<Item> items = Arrays.asList(new Item(BANANA, 0.20), new Item(BANANA, 0.20));
        //When
        BigDecimal price = noOfferPriceService.calculatePrice(items);

        //Then
        Assertions.assertEquals(BigDecimal.valueOf(0.40), price);
    }

    /**
     * Given a list of items.
     * <p>
     * When calculating the price without discount.
     * <p>
     * Then returns the final price.
     */
    @Test
    void calculatePriceForApplesTest() {
        //Given
        List<Item> items = Arrays.asList(new Item(APPLE, 0.35), new Item(APPLE, 0.35));
        //When
        BigDecimal price = noOfferPriceService.calculatePrice(items);

        //Then
        Assertions.assertEquals(BigDecimal.valueOf(0.70), price);
    }
}
