package com.mycompany.progexam;


public class Product implements IProduct {

    
    private final String[] productNames;
    private final int[][] productSales;

    public Product(String[] productNames, int[][] productSales) {
        this.productNames = productNames;
        this.productSales = productSales;
    }

    
    @Override
    public int GetTotalProductsSold() {
        int total = 0;

        
        for (int[] monthlySales : productSales) {
            for (int sale : monthlySales) {
                total += sale;
            }
        }
        return total;
    }

    
    @Override
    public double GetAverageProductSold() {
        int numberOfEntries = 0;

        
        for (int[] monthlySales : productSales) {
            numberOfEntries += monthlySales.length;
        }

        
        if (numberOfEntries == 0) {
            return 0;
        }

        
        return (double) GetTotalProductsSold() / numberOfEntries;
    }

    
    @Override
    public String GetMostPopularProduct() {
        if (productNames.length == 0) {
            return "No products captured";
        }

        int highestSale = Integer.MIN_VALUE;
        int popularIndex = 0;

        
        for (int i = 0; i < productSales.length; i++) {
            for (int j = 0; j < productSales[i].length; j++) {
                if (productSales[i][j] > highestSale) {
                    highestSale = productSales[i][j];
                    popularIndex = i;
                }
            }
        }

        return productNames[popularIndex] + "(" + highestSale + ")";
    }
}
