/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
public class UserImpl extends  UnicastRemoteObject implements UserInterface {

    public Connection conn;
    private Statement stmt;
    public UserImpl() throws RemoteException{
        String dbUrl = "jdbc:mysql://localhost:3306/exam";
        String dbClass = "com.mysql.jdbc.Driver";
        try {
            Class.forName(dbClass);
            conn = DriverManager.getConnection(dbUrl, "root", "123456789");
            stmt = conn.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public List<User> selectAll() throws RemoteException {
        List<User> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean deleteUser(int id) throws RemoteException {
        try {
            String sql = "DELETE FROM user WHERE id=" + id;
            if(stmt.executeUpdate(sql) != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
