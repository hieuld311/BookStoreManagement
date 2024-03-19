/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author hieul
 */
import java.util.Date;
import java.text.SimpleDateFormat;

public class TestCurrentDatetime {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentDateTime() {
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    public static void main(String[] args) {
        String currentDateTime = getCurrentDateTime();
        System.out.println("Current Date and Time: " + currentDateTime);
    }
}