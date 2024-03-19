/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.OrderDetails;
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
public class OrderDetailsDAO extends DBConnect {

    public int insertOrderDetails(OrderDetails obj) {
        int n = 0;
        String sql = "INSERT INTO [OrderDetails]([sku],[order_id],[price],[quantity],[total])\n"
                + "     VALUES(?,?,?,?,?)";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getSku());
            pre.setInt(2, obj.getOrderId());
            pre.setDouble(3, obj.getPrice());
            pre.setInt(4, obj.getQuantiy());
            pre.setDouble(5, obj.getTotal());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public int updateOrderDetails(OrderDetails obj) {
        int n = 0;
        String sql = "UPDATE [OrderDetails]\n"
                + "   SET [price] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[total] = ?\n"
                + " WHERE [sku] = ? and [order_id] = ?";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setDouble(1, obj.getPrice());
            pre.setInt(2, obj.getQuantiy());
            pre.setDouble(3, obj.getTotal());
            pre.setString(4, obj.getSku());
            pre.setInt(5, obj.getOrderId());
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

    public int deleteOrderDetails(String sku, int id) {
        int n = 0;
        String sql = "DELETE FROM [OrderDetails] WHERE [sku] = ? AND [order_id] = ?";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, sku);
            pre.setInt(2, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public Vector<OrderDetails> getData(String sql) {
        Vector<OrderDetails> vector = new Vector<OrderDetails>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, //hỗ trợ Thread Safe
                    ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String sku = rs.getString("sku");
                int orderId = rs.getInt("order_id");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                double total = rs.getDouble("total");
                OrderDetails obj = new OrderDetails(id, sku, orderId, price, quantity, total);
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

    public int getOrderedQuantityOf(String sku) {
        int n = 0;
        String sql = "SELECT SUM(quantity) AS orderedQuantity "
                + "FROM OrderDetails "
                + "WHERE sku = ?";
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, sku);
            rs = pre.executeQuery();
            while (rs.next()) {
                n = rs.getInt("orderedQuantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Log error or notify user here
                }
            }
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }
    
    public int removeCustId(int id){
        int n = 0;
        String sql = "DELETE FROM [OrderDetails] WHERE [order_id] = ?";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public static void main(String[] args) {
        OrderDetailsDAO dao = new OrderDetailsDAO();
        //    int n = dao.updateOrderDetails(new OrderDetails("TC7777", 118, 11.00, 1, 11.00, "Done"));
        //dao.deleteOrderDetails("TC7777", 118);
//        Vector<OrderDetails> vector = dao.getData("select * from OrderDetails");
//        for (OrderDetails OrderDetails : vector) {
//            System.out.println(OrderDetails);
//        }
    int n = dao.getOrderedQuantityOf("MC3021");
        System.out.println(n);
    }
}
