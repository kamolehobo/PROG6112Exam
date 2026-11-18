package com.mycompany.progexam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ProductCalculationTest {

    private ProductCalculations calc;

    
    @BeforeEach
    public void setUp() {
        calc = new ProductCalculations(3500);
    }

    // Unit Test 1
    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        assertEquals(525.0, calc.GetVat(), 0.01);
    }

    // Unit Test 2
    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        assertEquals(4200.0, calc.GetTotal(), 0.01);
    }
}