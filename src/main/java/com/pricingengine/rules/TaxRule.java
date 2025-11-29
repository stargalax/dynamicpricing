package com.pricingengine.rules;

import com.pricingengine.model.Product;
import com.pricingengine.model.PricingContext;

public class TaxRule implements PricingRule {

    @Override
    public double apply(Product product, PricingContext context, double currentPrice) {
        return currentPrice * 1.18; // 18% tax
    }

    @Override
    public String getName() {
        return "TaxRule";
    }
}
