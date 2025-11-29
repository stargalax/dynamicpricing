package com.pricingengine.pricing_api.controller;

import com.pricingengine.engine.PricingEngine;
import com.pricingengine.model.PriceResponse;
import com.pricingengine.model.Product;
import com.pricingengine.model.PricingContext;
import com.pricingengine.rules.CustomerDiscountRule;
import com.pricingengine.rules.TaxRule;
import com.pricingengine.rules.WeekendSurchargeRule;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/price")
public class PricingController {

    private final PricingEngine engine;

    public PricingController() {
        engine = new PricingEngine()
                .addRule(new CustomerDiscountRule())
                .addRule(new WeekendSurchargeRule())
                .addRule(new TaxRule());
    }

    // Endpoint to get just the final price (optional)
    @PostMapping
    public double calculatePrice(@RequestBody PriceRequest request) {
        Product product = new Product(request.getProductId(), request.getBasePrice());
        PricingContext context = new PricingContext(
                request.getCustomerType(),
                request.getLocation(),
                request.getTime()
        );
        return engine.calculatePrice(product, context);
    }

    // New endpoint to get detailed breakdown
    @PostMapping("/breakdown")
    public PriceResponse calculatePriceWithBreakdown(@RequestBody PriceRequest request) {
        Product product = new Product(request.getProductId(), request.getBasePrice());
        PricingContext context = new PricingContext(
                request.getCustomerType(),
                request.getLocation(),
                request.getTime()
        );
        return engine.calculatePriceWithBreakdown(product, context);
    }

    public static class PriceRequest {
        private String productId;
        private double basePrice;
        private String customerType;
        private String location;
        private LocalDateTime time;

        // getters & setters
        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }
        public double getBasePrice() { return basePrice; }
        public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
        public String getCustomerType() { return customerType; }
        public void setCustomerType(String customerType) { this.customerType = customerType; }
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
        public LocalDateTime getTime() { return time; }
        public void setTime(LocalDateTime time) { this.time = time; }
    }
}
