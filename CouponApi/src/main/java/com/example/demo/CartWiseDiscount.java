package com.example.demo;

import java.util.List;
import java.util.Map;

public class CartWiseDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(List<CartItem> cart, Map<String, Object> details) {
        double threshold = (double) details.get("threshold");
        double discount = (double) details.get("discount");

        double total = 0;
        for (CartItem item : cart) {
            total += item.getPrice() * item.getQuantity();
        }

        if (total > threshold) {
            return (total * discount) / 100.0;
        }
        return 0;
    }
}
