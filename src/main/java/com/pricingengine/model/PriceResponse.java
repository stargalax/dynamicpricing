package com.pricingengine.model;

import java.util.List;

public class PriceResponse {
    private double basePrice;
    private double finalPrice;
    private List<RuleApplied> appliedRules;

    public PriceResponse(double basePrice, double finalPrice, List<RuleApplied> appliedRules) {
        this.basePrice = basePrice;
        this.finalPrice = finalPrice;
        this.appliedRules = appliedRules;
    }

    public static class RuleApplied {
        private String ruleName;
        private double priceBefore;
        private double priceAfter;

        public RuleApplied(String ruleName, double priceBefore, double priceAfter) {
            this.ruleName = ruleName;
            this.priceBefore = priceBefore;
            this.priceAfter = priceAfter;
        }

        public String getRuleName() { return ruleName; }
        public double getPriceBefore() { return priceBefore; }
        public double getPriceAfter() { return priceAfter; }
    }

    public double getBasePrice() { return basePrice; }
    public double getFinalPrice() { return finalPrice; }
    public List<RuleApplied> getAppliedRules() { return appliedRules; }
}
