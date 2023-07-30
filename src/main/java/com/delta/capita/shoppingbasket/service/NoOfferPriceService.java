package com.delta.capita.shoppingbasket.service;

import com.delta.capita.shoppingbasket.model.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 * this class is used to calculate the total price of list of same items which has no offers.
 */
public class NoOfferPriceService implements IPriceService{
    @Override
    public BigDecimal calculatePrice(List<Item> items) {
        if (items.isEmpty()) {
            return BigDecimal.valueOf(0);
        }
        Item item = items.stream().findFirst().orElse(new Item());
        return BigDecimal.valueOf(items.size() * item.getPrice());
    }
}