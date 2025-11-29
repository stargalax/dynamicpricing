package com.pricingengine.model;

import java.time.LocalDateTime;

public class PricingContext {
    private final String customerType;
    private final String location;
    private final LocalDateTime time;

    public PricingContext(String customerType, String location, LocalDateTime time) {
        this.customerType = customerType;
        this.location = location;
        this.time = time;
    }

    public String getCustomerType() { return customerType; }
    public String getLocation() { return location; }
    public LocalDateTime getTime() { return time; }
}
