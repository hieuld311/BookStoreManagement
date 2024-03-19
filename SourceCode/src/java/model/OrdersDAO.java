/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieul
 */
public class OrdersDAO extends DBConnect {

    public int insertOrders(Orders obj) {
        int n = 0;
        String sql = "INSERT INTO [Orders]([dateBuy],[total],[name],[phone],[address],[username], [status])\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getDateBuy());
            pre.setDouble(2, obj.getTotal());
            pre.setString(3, obj.getName());
            pre.setString(4, obj.getPhone());
            pre.setString(5, obj.getAddress());
            pre.setString(6, obj.getUsername());
            pre.setString(7, obj.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public int updateOrders(Orders obj) {
        int n = 0;
        String sql = "UPDATE [Orders]\n"
                + "   SET [dateBuy] = ?\n"
                + "      ,[total] = ?\n"
                + "      ,[name] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[username] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE [orderId] = ?";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getDateBuy());
            pre.setDouble(2, obj.getTotal());
            pre.setString(3, obj.getName());
            pre.setString(4, obj.getPhone());
            pre.setString(5, obj.getAddress());
            pre.setString(6, obj.getUsername());
            pre.setString(7, obj.getStatus());
            pre.setInt(8, obj.getOrderID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Log error or notify user here
                }
            }
        }
        return n;
    }

    public int deleteOrders(int id) {
        int n = 0;
        String sql = "DELETE FROM Orders WHERE orderId = ?";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public Orders getNewestID() {
        Orders orders = null;
        Statement statement = null;
        ResultSet rs = null;
        String sql = "  SELECT * FROM Orders\n"
                + "WHERE [orderId] = (SELECT MAX([orderId]) FROM Orders);";
        try {
            statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, //hỗ trợ Thread Safe
                    ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String dateBuy = rs.getString("dateBuy");
                double total = rs.getDouble("total");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String username = rs.getString("username");
                String status = rs.getString("status");
                Orders obj = new Orders(orderID, dateBuy, total, name, address, phone, username, status);
                orders = obj;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Log error or notify user here
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Log error or notify user here
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Log error or notify user here
                }
            }
        }
        return orders;
    }

    public Vector<Orders> getData(String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, //hỗ trợ Thread Safe
                    ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String dateBuy = rs.getString("dateBuy");
                double total = rs.getDouble("total");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String username = rs.getString("username");
                String status = rs.getString("status").trim();
                Orders obj = new Orders(orderID, dateBuy, total, name, address, phone, username, status);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Log error or notify user here
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Log error or notify user here
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Log error or notify user here
                }
            }
        }
        return vector;
    }

    public static void main(String[] args) {
        OrdersDAO dao = new OrdersDAO();
        //    int n = dao.updateOrders(new Orders(117, "2023-10-31", 11.00, "Khoai Tay", "HCM, VietNam", "0912333333", "khoaitay"));
//        dao.deleteOrders(117);
//        Vector<Orders> vector = dao.getData("select * from Orders");
//        for (Orders Orders : vector) {
//            System.out.println(Orders);
//        }
        dao.updateOrders(new Orders(128, "2023-11-06 00:11:02.000", 54.92, "Khoai", "02223334444", "fpt", "khoaitay", "processing"));
    }
}
