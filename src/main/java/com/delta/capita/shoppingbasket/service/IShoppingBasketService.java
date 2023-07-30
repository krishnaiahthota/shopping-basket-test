package com.delta.capita.shoppingbasket.service;

import java.math.BigDecimal;
import java.util.List;

public interface IShoppingBasketService {

    BigDecimal calculatePrice(List<String> items);
}
