package com.example.demo;

import java.util.List;
import java.util.Map;

public class ProductWiseDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(List<CartItem> cart, Map<String, Object> details) {
        int targetProduct = (int) details.get("product_id");
        double discount = (double) details.get("discount");

        double totalDiscount = 0;
        for (CartItem item : cart) {
            if (item.getProductId() == targetProduct) {
                totalDiscount += (item.getPrice() * item.getQuantity() * discount) / 100.0;
            }
        }
        return totalDiscount;
    }
}
