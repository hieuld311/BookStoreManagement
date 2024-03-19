/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Account;
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
public class AccountDAO extends DBConnect {

    public int createAccount(Account obj) {
        int n = 0;
        String sql = "INSERT INTO Account\n"
                + "     VALUES (?,?,?,?)";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getUsername());
            pre.setString(2, obj.getPassword());
            pre.setString(3, obj.getFullName());
            pre.setInt(4, obj.getIsAdmin());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public int updateAccount(Account obj) {
        int n = 0;
        String sql = "UPDATE [Account]\n"
                + "   SET [password] = ?\n"
                + "      ,[fullname] = ?\n"
                + "      ,[isAdmin] = ?\n"
                + " WHERE [username] = ?";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getPassword());
            pre.setString(2, obj.getFullName());
            pre.setInt(3, obj.getIsAdmin());
            pre.setString(4, obj.getUsername());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Log error or notify user here
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

    public int updatePasswordAccount(String username, String password) {
        int n = 0;
        String sql = "UPDATE [Account]\n"
                + "   SET [password] = ?\n"
                + " WHERE [username] = ?";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, password);
            pre.setString(2, username);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Log error or notify user here
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

    public int updateRoleAccount(String username, int role) {
        int n = 0;
        String sql = "UPDATE [Account]\n"
                + "   SET [isAdmin] = ?\n"
                + " WHERE [username] = ?";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(1, role);
            pre.setString(2, username);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Log error or notify user here
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

    public int deleteAccount(String username) {
        int n = 0;
        String sql = "DELETE FROM Account WHERE username = ?";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public Vector<Account> getData(String sql) {
        Vector<Account> vector = new Vector<Account>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, //hỗ trợ Thread Safe
                    ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullname");
                int isAdmin = rs.getInt("isAdmin");
                Account obj = new Account(username, password, fullName, isAdmin);
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

    public Account checklogin(String username, String password) {
        String sql = "select * from Account where username = ? and password = ?";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int isAuthorized(String username) {
        int n = 0;
        String sql = "select isAdmin from Account where username = ?";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
                return n;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
//        int n = dao.updateAccount(new Account("khoaichien", "12345", "Khoai Chien", 0));
//        dao.deleteAccount("khoaichien");
//        Vector<Account> vector = dao.getData("select * from Account");
//        for (Account Account : vector) {
//            System.out.println(Account);}
//        int n = dao.isAuthorized("khoaitay");
//        if (n > 0) {
//            System.out.println("Authorized");
//        } else {
//            System.out.println("not authorized");
//        }
        Account n = dao.checklogin("admin", "123");
        if (n == null) {
            System.out.println("Cant");
        } else {
            System.out.println("logged in");
        }
    }
}
