package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/coupons")
public class CouponController {
    private final CouponService service;

    public CouponController(CouponService service) {
        this.service = service;
    }

    @PostMapping
    public Coupon createCoupon(@RequestBody Coupon c) {
        return service.createCoupon(c);
    }

    @GetMapping
    public List<Coupon> getCoupons() {
        return service.getAllCoupons();
    }

    @GetMapping("/{id}")
    public Coupon getCoupon(@PathVariable int id) {
        return service.getCoupon(id);
    }

    @PutMapping("/{id}")
    public Coupon updateCoupon(@PathVariable int id, @RequestBody Coupon c) {
        return service.updateCoupon(id, c);
    }

    @DeleteMapping("/{id}")
    public String deleteCoupon(@PathVariable int id) {
        return service.deleteCoupon(id) ? "Deleted" : "Not Found";
    }

    @PostMapping("/apply/{id}")
    public Map<String, Object> applyCoupon(@PathVariable int id, @RequestBody Map<String, Object> payload) {
        Coupon coupon = service.getCoupon(id);
        if (coupon == null) return Map.of("error", "Coupon not found");

        List<Map<String, Object>> items = (List<Map<String, Object>>) payload.get("cart");
        List<CartItem> cart = new ArrayList<>();
        for (Map<String, Object> it : items) {
            cart.add(new CartItem((int) it.get("product_id"), (int) it.get("quantity"), ((Number) it.get("price")).doubleValue()));
        }

        double discount = service.calculateDiscount(cart, coupon);
        return Map.of("coupon_id", id, "discount", discount);
    }
}