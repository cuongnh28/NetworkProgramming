/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Users;

/**
 *
 * @author The Godfather
 */
public class Dao {
    
    private Connection conn;
    private Statement stmt;
    
    public Dao(){
        String dbUrl = "jdbc:mysql://localhost:3306/exam";
        String dbClass = "com.mysql.jdbc.Driver";
        try {
            Class.forName(dbClass);
            conn = DriverManager.getConnection(dbUrl, "root", "123456789");
            stmt = conn.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkLogin(Users user){
        String sql = "SELECT * FROM users WHERE username ='" + user.getUsername() + "' AND password ='"
                + user.getPassword() + "'";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean editUser(Users user){
        String sql = "UPDATE users SET username='" + user.getUsername() + "', password ='" + user.getPassword() 
                + "', name='" + user.getName() + "', address='" + user.getAddress() +"' WHERE id=" + user.getId();
        System.out.println(sql);
        try {
            if(stmt.executeUpdate(sql) != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Users loadUsers(String username, String password){
        Users user = null;
        String sql = "SELECT * FROM users WHERE name = '" + username + "' AND password = '" + password + "'";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public boolean deleteUser(int id){
        String sql = "DELETE FROM users WHERE id = " + id;
        try {
            if(stmt.executeUpdate(sql) != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
