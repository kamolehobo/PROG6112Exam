package com.mycompany.progexam;

import java.util.Scanner;


public class ProductSalesReport {

    
    private static final int NUMBER_OF_MONTHS = 3;

    public static void main(String[] args) {

        
        String[] productNames = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };

        
        int[][] productSales = new int[productNames.length][NUMBER_OF_MONTHS];

        
        try (Scanner input = new Scanner(System.in)) {

            
            for (int i = 0; i < productNames.length; i++) {
                for (int j = 0; j < NUMBER_OF_MONTHS; j++) {
                    String prompt = "Enter number of sales for month " + (j + 1)
                            + " for " + productNames[i] + " mattress: ";
                    productSales[i][j] = readNonNegativeInt(input, prompt);
                }
                System.out.println(); 
            }
        }

        
        IProduct product = new Product(productNames, productSales);
        PrintProduct printer = new PrintProduct(product);
        printer.PrintReport();
    }

    
    private static int readNonNegativeInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String entry = input.nextLine().trim();
            try {
                int value = Integer.parseInt(entry);
                if (value >= 0) {
                    return value;
                }
                System.out.println("Sales cannot be negative. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }
}