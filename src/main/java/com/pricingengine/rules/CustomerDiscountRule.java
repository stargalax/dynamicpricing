package com.pricingengine.rules;

import com.pricingengine.model.Product;
import com.pricingengine.model.PricingContext;

public class CustomerDiscountRule implements PricingRule {

    @Override
    public double apply(Product product, PricingContext context, double currentPrice) {
        if ("GOLD".equalsIgnoreCase(context.getCustomerType())) {
            return currentPrice * 0.90; // 10% discount
        }else if("SILVER".equalsIgnoreCase(context.getCustomerType())) {
        	 return currentPrice * 0.80;
        }
        return currentPrice;
    }

    @Override
    public String getName() {
        return "CustomerDiscountRule";
    }
}
