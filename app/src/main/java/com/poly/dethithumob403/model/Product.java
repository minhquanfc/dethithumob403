package com.poly.dethithumob403.model;

public class Product {
    public String pid;
    public String name;
    public int price;

    public Product() {
    }

    public Product(String pid, String name, int price) {
        this.pid = pid;
        this.name = name;
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
