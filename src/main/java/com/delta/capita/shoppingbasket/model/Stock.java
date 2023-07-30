package com.delta.capita.shoppingbasket.model;

import com.delta.capita.shoppingbasket.constants.OfferType;
import com.delta.capita.shoppingbasket.service.IPriceService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.ConcurrentMap;


/**
 *  this class contains the Stock and offers. pricing should be dynamic based on the offers.
 *  pricing strategy is added here which should be removed in general and we should have a special service for that.
 */
@Getter
@AllArgsConstructor
public class Stock {
    private ConcurrentMap<String, Item> itemsMap;
    private ConcurrentMap<String, OfferType> offers;
    private ConcurrentMap<OfferType, IPriceService> pricingStrategyMap;
}