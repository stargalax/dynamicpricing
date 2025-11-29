package com.pricingengine.model;

public class Product {
    private final String id;
    private final double basePrice;

    public Product(String id, double basePrice) {
        this.id = id;
        this.basePrice = basePrice;
    }

    public String getId() { return id; }
    public double getBasePrice() { return basePrice; }
}
