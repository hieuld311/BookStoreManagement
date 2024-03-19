/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author hieul
 */
import java.io.Serializable;

/**
 *
 * @author MSI
 */
public class OrderDetails implements Serializable {
    private int id;
    private String sku; 
    private int orderId;
    private double price;
    private int quantiy;
    private double total;

    public OrderDetails() {
    }

    public OrderDetails(String sku, int orderId, double price, int quantiy, double total) {
        this.sku = sku;
        this.orderId = orderId;
        this.price = price;
        this.quantiy = quantiy;
        this.total = total;
    }

    public OrderDetails(int id, String sku, int orderId, double price, int quantiy, double total) {
        this.id = id;
        this.sku = sku;
        this.orderId = orderId;
        this.price = price;
        this.quantiy = quantiy;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantiy() {
        return quantiy;
    }

    public void setQuantiy(int quantiy) {
        this.quantiy = quantiy;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "id=" + id + ", sku=" + sku + ", orderId=" + orderId + ", price=" + price + ", quantiy=" + quantiy + ", total=" + total + '}';
    }


  
}

