package com.pricingengine.rules;

import com.pricingengine.model.Product;
import com.pricingengine.model.PricingContext;

public interface PricingRule {
    double apply(Product product, PricingContext context, double currentPrice);
    String getName();
}
