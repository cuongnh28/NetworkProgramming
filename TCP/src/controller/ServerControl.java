/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author The Godfather
 */
public class ServerControl {

    private Connection conn;
    private ServerSocket myServer;
    private int port = 6666;

    public ServerControl() {
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbClass = "com.mysql.jdbc.Driver";
        try {
            myServer = new ServerSocket(port);
            Class.forName(dbClass);
            conn = DriverManager.getConnection(dbUrl, "root", "123456789");
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            listening();
        }
    }

    public void listening() {
        try {
            Socket clientSocket = myServer.accept();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            Object o = ois.readObject();
            User user = (User) o;
            if (checkLogin(user)) {
                oos.writeObject("ok");
            } else if (addUser(user)) {
                oos.writeObject("ok");
            } else {
                oos.writeObject("false");
            }
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean checkLogin(User user) {
        String sql = "SELECT * FROM user WHERE username='" + user.getUsername() + "' AND password='" + user.getPassword() + "'";
        System.out.println(sql);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Boolean addUser(User user) {
        String sql = "INSERT INTO user(username,password) values('" + user.getUsername() + "','" + user.getPassword() + "');";
        try {
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate(sql);
            if (rs != -1) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
