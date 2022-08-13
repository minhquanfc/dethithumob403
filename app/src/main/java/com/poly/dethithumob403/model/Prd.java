package com.poly.dethithumob403.model;

import java.util.ArrayList;

public class Prd {
    public ArrayList<Product> products;

    public Prd() {
    }

    public Prd(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
