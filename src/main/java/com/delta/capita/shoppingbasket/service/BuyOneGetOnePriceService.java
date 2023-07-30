package com.delta.capita.shoppingbasket.service;

import com.delta.capita.shoppingbasket.model.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 * this class is for Buy One Get One offer purpose.
 */
public class BuyOneGetOnePriceService implements IPriceService{

    /**
     * this method calculate the final price of the discounted items.
     * @param items total items.
     * @return total price after the discount.
     */
    @Override
    public BigDecimal calculatePrice(List<Item> items) {
        if (items.isEmpty()) {
            return BigDecimal.valueOf(0);
        }
        // grouped items to apply offer
        int groupedItems = items.size() / 2;
        // individual items not qualified for offer
        int remainingItems = items.size() % 2;
        Item item = items.stream().findAny().orElse(new Item());
        return BigDecimal.valueOf(groupedItems * item.getPrice() + remainingItems * item.getPrice());
    }
}
