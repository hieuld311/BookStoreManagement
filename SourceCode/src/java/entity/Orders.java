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
public class Orders implements Serializable {

    private int orderID;
    private String dateBuy;
    private double total;
    private String name;
    private String phone;
    private String address;
    private String username;
    private String status;

    public Orders(String dateBuy, double total, String name, String phone, String address, String username, String status) {
        this.dateBuy = dateBuy;
        this.total = total;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.status = status;
    }

    public Orders() {
    }

    public Orders(int orderID, String dateBuy, double total, String name, String phone, String address, String username, String status) {
        this.orderID = orderID;
        this.dateBuy = dateBuy;
        this.total = total;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(String dateBuy) {
        this.dateBuy = dateBuy;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderID=" + orderID + ", dateBuy=" + dateBuy + ", total=" + total + ", name=" + name + ", phone=" + phone + ", address=" + address + ", username=" + username + ", status=" + status + '}';
    }




}
