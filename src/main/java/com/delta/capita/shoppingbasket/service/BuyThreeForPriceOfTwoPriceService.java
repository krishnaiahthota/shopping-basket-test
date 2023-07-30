package com.delta.capita.shoppingbasket.service;

import com.delta.capita.shoppingbasket.model.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 * this class is for Buy three for price of two offer purpose.
 */
public class BuyThreeForPriceOfTwoPriceService implements IPriceService{

    /**
     * this method calculates the final price of the items after discount.
     * @param items List of discounted items
     * @return the final price after discount.
     */
    @Override
    public BigDecimal calculatePrice(List<Item> items) {
        if (items.isEmpty()) {
            return BigDecimal.valueOf((double) 0);
        }
        // grouped items to apply offer
        int groupedItems = items.size() / 3;
        // individual items not qualified for offer
        int remainingItems = items.size() % 3;
        Item item = items.stream().findFirst().orElse(new Item());
        return BigDecimal.valueOf(groupedItems * item.getPrice() * 2 + remainingItems * item.getPrice());
    }
}
