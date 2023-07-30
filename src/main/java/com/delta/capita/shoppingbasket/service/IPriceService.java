package com.delta.capita.shoppingbasket.service;

import com.delta.capita.shoppingbasket.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface IPriceService {
    BigDecimal calculatePrice(List<Item> items);
}
