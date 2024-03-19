/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Product;
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
public class ProductDAO extends DBConnect {

    public int insertProduct(Product obj) {
        int n = 0;
        String sql = "INSERT INTO [Product]([sku],[name],[type],[description],[quantity],[price], [status])\n"
                + "     VALUES(?,?,?,?,?,?)";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getSku());
            pre.setString(2, obj.getName());
            pre.setString(3, obj.getType());
            pre.setString(4, obj.getDescription());
            pre.setInt(5, obj.getQuantity());
            pre.setDouble(6, obj.getPrice());
            pre.setInt(7, obj.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public int updateProduct(Product obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET \n"
                + "      [name] = ?\n"
                + "      ,[type] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE [sku] = ?";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getName());
            pre.setString(2, obj.getType());
            pre.setString(3, obj.getDescription());
            pre.setInt(4, obj.getQuantity());
            pre.setDouble(5, obj.getPrice());
            pre.setInt(6, obj.getStatus());
            pre.setString(7, obj.getSku());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public int deleteProduct(String sku) {
        int n = 0;
        String sql = "DELETE FROM Product WHERE sku = ?";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, sku);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public Vector<Product> getData(String sql) {
        Vector<Product> vector = new Vector<Product>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, //hỗ trợ Thread Safe
                    ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                String sku = rs.getString("sku");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                int status = rs.getInt("status");
                Product obj = new Product(sku, name, type, description, quantity, price, status);
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

    public Product getProduct(String sku) {
        String sql = "SELECT name, description, quantity, price\n"
                + "                  FROM Product \n"
                + "                  WHERE sku = ?";
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, sku);
            rs = pre.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                Product prd = new Product(sku, name, name, description, quantity, price);
                return prd;
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
        return null;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        //    int n = dao.updateProduct(new Product("Book1", "OverLord", "Light Novel", "....too long", 10, 11.000));
//        dao.deleteProduct("Book1");
        Vector<Product> vector = dao.getData("select * from Product");
        for (Product Product : vector) {
            System.out.println(Product);
              }
//        if (vector.isEmpty()) {
//            System.out.println("null");
//        }
    }
}
