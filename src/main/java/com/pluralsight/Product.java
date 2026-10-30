package com.pluralsight;

public class Product {

    private int productId;
    private String name;
    private double price;

    public Product(int productId, String name, double price) {
        this.name = name;
        this.price = price;
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getProductId() {
        return productId;
    }
}
