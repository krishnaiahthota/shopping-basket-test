package com.delta.capita.shoppingbasket.service;


import com.delta.capita.shoppingbasket.constants.OfferType;
import com.delta.capita.shoppingbasket.model.Item;
import com.delta.capita.shoppingbasket.model.Stock;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * this class is for shopping basket operation, for now we have only calculating the price.
 */
@Setter
@Getter
public class ShoppingBasketService implements IShoppingBasketService {

    // it should be dynamic service, to make it simple I added this.
    private Stock stock;

    /**
     * this method calculates the final prices of the shopping basket items
     * @param itemNames shopping basket items
     * @return final price of the items in the basket.
     */
    @Override
    public BigDecimal calculatePrice(List<String> itemNames) {
        // need to add the validations on the input, throw custom exception in case of the invalid cases.
        ConcurrentMap<String, List<Item>> itemsMap = obtainItemsMap(itemNames);
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        totalPrice = calculatePriceAfterDiscounts(itemsMap, totalPrice);
        return totalPrice;
    }

    private BigDecimal calculatePriceAfterDiscounts(ConcurrentMap<String, List<Item>> itemsMap, BigDecimal totalPrice) {
        for(Map.Entry<String, List<Item>> entry : itemsMap.entrySet()) {
            OfferType offerType = stock.getOffers().get(entry.getKey());
            IPriceService priceStrategy = stock.getPricingStrategyMap().get(offerType);
            totalPrice = totalPrice.add(priceStrategy.calculatePrice(entry.getValue()));
        }
        return totalPrice;
    }

    private ConcurrentMap<String, List<Item>> obtainItemsMap(List<String> itemNames) {
        return itemNames
                .stream()
                .map(item -> stock.getItemsMap().get(item))
                .collect(Collectors.groupingByConcurrent(Item::getName));
    }
}
