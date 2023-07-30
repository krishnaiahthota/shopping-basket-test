package com.delta.capita.shoppingbasket.service;

import com.delta.capita.shoppingbasket.constants.OfferType;
import com.delta.capita.shoppingbasket.model.Item;
import com.delta.capita.shoppingbasket.model.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.delta.capita.shoppingbasket.constants.ItemNameConstants.APPLE;
import static com.delta.capita.shoppingbasket.constants.ItemNameConstants.BANANA;
import static com.delta.capita.shoppingbasket.constants.ItemNameConstants.LIME;
import static com.delta.capita.shoppingbasket.constants.ItemNameConstants.MELON;

/**
 * this class contains only positive cases.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShoppingBasketServiceTest {

    private ShoppingBasketService shoppingBasketService;


    @BeforeAll
    public void setup(){
        ConcurrentMap<String, Item> itemsMap = new ConcurrentHashMap<>();
        ConcurrentMap<String, OfferType> offers = new ConcurrentHashMap<>();
        ConcurrentMap<OfferType, IPriceService> pricingStrategyMap  = new ConcurrentHashMap<>();

        itemsMap.put(APPLE, new Item(APPLE, 0.35));
        itemsMap.put(BANANA, new Item(BANANA, 0.20));
        itemsMap.put(MELON, new Item(MELON, 0.50));
        itemsMap.put(LIME, new Item(LIME, 0.15));

        offers.put(APPLE, OfferType.NO_OFFER);
        offers.put(BANANA, OfferType.NO_OFFER);
        offers.put(MELON, OfferType.BUY_ONE_GET_ONE);
        offers.put(LIME, OfferType.BUY_THREE_FOR_PRICE_OF_TWO);

        pricingStrategyMap.put(OfferType.NO_OFFER, new NoOfferPriceService());
        pricingStrategyMap.put(OfferType.BUY_ONE_GET_ONE, new BuyOneGetOnePriceService());
        pricingStrategyMap.put(OfferType.BUY_THREE_FOR_PRICE_OF_TWO, new BuyThreeForPriceOfTwoPriceService());

        Stock stock = new Stock(itemsMap, offers, pricingStrategyMap);

        shoppingBasketService = new ShoppingBasketService();
        shoppingBasketService.setStock(stock);

    }

    /**
     * Given a list of item names
     * <p>
     * When calculate the price of the shopping cart
     * <p>
     * Then returns the price
     */
    @Test
    void calculatePriceTestPositiveCase() {
        // Given
        List<String> itemNames = Arrays.asList(APPLE, BANANA, LIME, MELON, LIME, MELON, LIME);

        // When
        BigDecimal finalPrice = shoppingBasketService.calculatePrice(itemNames);

        //Then
        Assertions.assertNotEquals(BigDecimal.valueOf(0), finalPrice);
        Assertions.assertEquals(BigDecimal.valueOf(1.35), finalPrice);
    }

    /**
     * Given a list of item names
     * <p>
     * When calculate the price of the shopping cart
     * <p>
     * Then returns the price
     */
    @Test
    void calculatePriceTestPositiveCase2() {
        // Given
        List<String> itemNames = Arrays.asList(APPLE, BANANA, LIME, MELON, LIME, MELON, LIME, BANANA);

        // When
        BigDecimal finalPrice = shoppingBasketService.calculatePrice(itemNames);

        //Then
        Assertions.assertNotEquals(BigDecimal.valueOf(0), finalPrice);
        Assertions.assertEquals(BigDecimal.valueOf(1.55), finalPrice);
    }
}
