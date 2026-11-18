package com.mycompany.progexam;


public class PrintProduct {

    private static final String REPORT_TITLE = "PRODUCT SALES REPORT - 2026";
    private static final String SEPARATOR = "*".repeat(45);

    
    private final IProduct product;

    public PrintProduct(IProduct product) {
        this.product = product;
    }

    
    public void PrintReportHeader() {
        System.out.println();
        System.out.println(REPORT_TITLE);
        System.out.println();
        System.out.println(SEPARATOR);
    }

    
    public void PrintTotalProductSales() {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
    }

    
    public void PrintAverageProductSales() {
        System.out.println("AVERAGE PRODUCT SALES: "
                + Math.round(product.GetAverageProductSold()));
    }

    
    public void PrintMostPopularProduct() {
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
    }

    
    public void PrintReport() {
        PrintReportHeader();
        PrintTotalProductSales();
        PrintAverageProductSales();
        PrintMostPopularProduct();
    }
}