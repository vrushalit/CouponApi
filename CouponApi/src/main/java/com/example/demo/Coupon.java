package com.example.demo;

import java.util.Map;

public class Coupon {
    private int id;
    private String type; // cart-wise, product-wise, bxgy
    private Map<String, Object> details;

    public Coupon(int id, String type, Map<String, Object> details) {
        this.id = id;
        this.type = type;
        this.details = details;
    }

    public int getId() { return id; }
    public String getType() { return type; }
    public Map<String, Object> getDetails() { return details; }

    public void setId(int id) { this.id = id; }
    public void setType(String type) { this.type = type; }
    public void setDetails(Map<String, Object> details) { this.details = details; }
}
