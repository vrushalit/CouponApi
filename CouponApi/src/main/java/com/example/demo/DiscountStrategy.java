package com.example.demo;
import java.util.List;
import java.util.Map;

public interface DiscountStrategy {
    double applyDiscount(List<CartItem> cart, Map<String, Object> details);
}

