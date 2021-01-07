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
import model.User;

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
    
    public boolean addUser(User user){
        String sql = "INSERT INTO user (name, birth, address) VALUES ('" + user.getName() + "', '" 
                + user.getBirth() + "', '" + user.getAddress() + "')";
        try {
            if(stmt.executeUpdate(sql) != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<User> searchByName(String name){
        String sql = "SELECT * FROM user WHERE name LIKE '%" + name + "%'";
        List<User> list = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
