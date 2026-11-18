package com.mycompany.progexam;

/**
 * Performs the sale calculations for a single product price.
 * VAT = 15% of price, Commission = 5% of price, Total = Price + VAT + Commission.
 */
public class ProductCalculations implements IProductCalculations {

    
    private static final double VAT_RATE = 0.15;
    private static final double COMMISSION_RATE = 0.05;

    private final double price;

    public ProductCalculations(double price) {
        this.price = price;
    }

    
    @Override
    public double GetVat() {
        return roundToCents(price * VAT_RATE);
    }

    
    @Override
    public double GetCommission() {
        return roundToCents(price * COMMISSION_RATE);
    }

    
    @Override
    public double GetTotal() {
        return roundToCents(price + GetVat() + GetCommission());
    }

    
    private double roundToCents(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
}