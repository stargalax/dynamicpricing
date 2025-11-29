package com.pricingengine.engine;

import com.pricingengine.model.PriceResponse;
import com.pricingengine.model.Product;
import com.pricingengine.model.PricingContext;
import com.pricingengine.rules.PricingRule;

import java.util.ArrayList;
import java.util.List;

public class PricingEngine {

    private final List<PricingRule> rules;

    // Default constructor
    public PricingEngine() {
        this.rules = new ArrayList<>();
    }

    // Constructor with rules
    public PricingEngine(List<PricingRule> rules) {
        this.rules = rules;
    }

    // Add a rule (for chaining)
    public PricingEngine addRule(PricingRule rule) {
        this.rules.add(rule);
        return this;
    }

    // Calculate final price only
    public double calculatePrice(Product product, PricingContext context) {
        double price = product.getBasePrice();
        for (PricingRule rule : rules) {
            price = rule.apply(product, context, price);
        }
        return price;
    }

    // Calculate price with breakdown
    public PriceResponse calculatePriceWithBreakdown(Product product, PricingContext context) {
        double price = product.getBasePrice();
        List<PriceResponse.RuleApplied> appliedRules = new ArrayList<>();

        for (PricingRule rule : rules) {
            double before = price;
            double after = rule.apply(product, context, price);
            if (after != before) {
                appliedRules.add(new PriceResponse.RuleApplied(rule.getName(), before, after));
            }
            price = after;
        }

        return new PriceResponse(product.getBasePrice(), price, appliedRules);
    }
}
