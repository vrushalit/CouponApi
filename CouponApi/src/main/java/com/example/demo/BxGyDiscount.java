package com.example.demo;

import java.util.List;
import java.util.Map;

public class BxGyDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(List<CartItem> cart, Map<String, Object> details) {
        List<Map<String, Object>> buyProducts = (List<Map<String, Object>>) details.get("buy_products");
        List<Map<String, Object>> getProducts = (List<Map<String, Object>>) details.get("get_products");
        int repetitionLimit = (int) details.get("repition_limit");

        int totalEligibleBuys = 0;
        for (Map<String, Object> bp : buyProducts) {
            int pid = (int) bp.get("product_id");
            int qty = (int) bp.get("quantity");
            for (CartItem item : cart) {
                if (item.getProductId() == pid) {
                    totalEligibleBuys += item.getQuantity() / qty;
                }
            }
        }

        int timesApplicable = Math.min(totalEligibleBuys, repetitionLimit);
        double totalDiscount = 0;

        for (int i = 0; i < timesApplicable; i++) {
            for (Map<String, Object> gp : getProducts) {
                int gpid = (int) gp.get("product_id");
                int gqty = (int) gp.get("quantity");
                for (CartItem item : cart) {
                    if (item.getProductId() == gpid) {
                        totalDiscount += gqty * item.getPrice();
                    }
                }
            }
        }
        return totalDiscount;
    }
}
