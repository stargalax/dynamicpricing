package com.pricingengine.rules;

import com.pricingengine.model.Product;
import com.pricingengine.model.PricingContext;
import java.time.DayOfWeek;
import java.time.Month;

public class WeekendSurchargeRule implements PricingRule {

    @Override
    public double apply(Product product, PricingContext context, double currentPrice) {
        DayOfWeek day = context.getTime().getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return currentPrice * 1.05; 
        }
        if (context.getTime().getMonth() == Month.DECEMBER && context.getTime().getDayOfMonth() == 25) {
            return currentPrice * 0.90; // 10% off for Christmas
        }
        int hour = context.getTime().getHour();
        if (hour >= 18 && hour <= 21) { // evening peak
            return currentPrice * 1.10; // 10% surcharge
        }
        if ((day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) && hour >= 18) {
            return currentPrice * 1.15; // 15% combined surcharge
        }



        return currentPrice;
    }

    @Override
    public String getName() {
        return "WeekendSurchargeRule";
    }
}
