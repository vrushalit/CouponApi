package com.example.demo;

package com.monkcommerce.coupons;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CouponService {
    private Map<Integer, Coupon> coupons = new HashMap<>();
    private int nextId = 1;

    public Coupon createCoupon(Coupon c) {
        c.setId(nextId++);
        coupons.put(c.getId(), c);
        return c;
    }

    public List<Coupon> getAllCoupons() {
        return new ArrayList<>(coupons.values());
    }

    public Coupon getCoupon(int id) {
        return coupons.get(id);
    }

    public Coupon updateCoupon(int id, Coupon c) {
        if (!coupons.containsKey(id)) return null;
        c.setId(id);
        coupons.put(id, c);
        return c;
    }

    public boolean deleteCoupon(int id) {
        return coupons.remove(id) != null;
    }

    public double calculateDiscount(List<CartItem> cart, Coupon coupon) {
        DiscountStrategy strategy;
        switch (coupon.getType()) {
            case "cart-wise": strategy = new CartWiseDiscount(); break;
            case "product-wise": strategy = new ProductWiseDiscount(); break;
            case "bxgy": strategy = new BxGyDiscount(); break;
            default: return 0;
        }
        return strategy.applyDiscount(cart, coupon.getDetails());
    }
}
