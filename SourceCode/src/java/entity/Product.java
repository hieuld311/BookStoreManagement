/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author hieul
 */
public class Product implements Serializable { /// ~ a model use in bussiness logic-tier (not mapping), use to present product in cart,  and product in order prepared

    private String sku;
    private String name;
    private String type;
    private String description;
    private int quantity;
    private double price;
    private int status;

    public Product() {
    }

    public Product(String sku, String name, int quantity, double price) {
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String sku, String name, String type, String description, int quantity, double price) {
        this.sku = sku;
        this.name = name;
        this.type = type;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String sku, String name, String type, String description, int quantity, double price, int status) {
        this.sku = sku;
        this.name = name;
        this.type = type;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "sku=" + sku + ", name=" + name + ", type=" + type + ", description=" + description + ", quantity=" + quantity + ", price=" + price + ", status=" + status + '}';
    }


}
