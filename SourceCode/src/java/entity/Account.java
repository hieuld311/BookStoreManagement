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
public class Account implements Serializable {
    private String username;
    private String password;
    private String fullName;
    private int isAdmin;

    public Account() {
    }

    public Account(String username, String password, String fullName, int isAdmin) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Account{" + "username=" + username + ", password=" + password + ", fullName=" + fullName + ", isAdmin=" + isAdmin + '}';
    }


    
    
}

