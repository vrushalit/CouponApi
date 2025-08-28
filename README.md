# Coupons Management API

## Overview
This is a **Spring Boot REST API** to manage and apply discount coupons for an e-commerce platform.  
The implementation uses the **Strategy Pattern** for applying different coupon types and stores all data in **memory** using Java ArrayList and HashMap (no external DB).  

The focus is on showing different coupon scenarios, extensibility for future coupon types, and documenting assumptions and limitations.

---

## Implemented Cases

### 1. Cart-wise Coupons
- **Description:** Discount applies to the whole cart if the cart total crosses a given threshold.
- **Example:** 10% off if cart total > 100.  
- **Logic:**  
  - System checks total cart amount.  
  - If threshold is met, applies the percentage discount on the entire cart.

### 2. Product-wise Coupons
- **Description:** Discount applies to specific product(s).  
- **Example:** 20% off on Product A.  
- **Logic:**  
  - System checks if the product is in the cart.  
  - If present, discount is applied only to that product’s price.

### 3. BxGy Coupons (Buy X Get Y)
- **Description:** Customer buys some products (from “buy” list) and gets some products (from “get” list) for free.  
- **Features:**
  - Handles a **repetition limit** (e.g., Buy 2 Get 1 up to 3 times).  
  - Works across multiple eligible products.  
- **Example:** Buy 2 of X or Y, Get 1 of Z free.  
- **Logic:**  
  - Count how many “buy” items cart has.  
  - Give corresponding “get” items free (up to the repetition limit).  

---

## Unimplemented Cases


1. **Coupon Expiry Dates / Validity Period**
   - Not implemented, but could be added with a validFrom and validTo field.

2. **Max Discount Cap**
   - Sometimes discounts have a max cap (e.g., 10% off up to ₹100).  
   - Not implemented due to time constraints.

---

## Limitations

- Coupons are stored **in memory only** → all data resets when the application restarts.  
- No persistence layer (no database).  
- No authentication or user-specific restrictions.  
- Only basic error handling (e.g., coupon not found, invalid input).  
- No logic for conflicts when multiple coupons are applicable.  
- Cart assumes product price comes from the request payload (not from a product catalog).  

---

## Assumptions

- **One coupon at a time:** Only one coupon can be applied to a cart in a single API call.  
- **Product prices are passed in request payload:** No external product service is queried.  
- **Quantities are always positive integers.**  
- **Coupons are global:** Any user can use them (no user-specific restrictions).  
- **Cart validation is simple:** No inventory/stock checks are performed.  

---

## API Endpoints

- POST /coupons → Create a new coupon  
- GET /coupons → Get all coupons  
- GET /coupons/{id} → Get coupon by ID  
- PUT /coupons/{id} → Update coupon by ID  
- DELETE /coupons/{id} → Delete coupon by ID  
- POST /applicable-coupons → Get applicable coupons for a given cart  
- POST /apply-coupon/{id} → Apply a specific coupon to a cart  

---

