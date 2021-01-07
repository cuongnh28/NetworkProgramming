/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author The Godfather
 */
public class ServerControl extends UnicastRemoteObject implements LoginInterface{
    
    private Connection conn;
    private int serverPort = 6666;
    private Registry registry;
    private String rmiService = "rmiService";
    
    public ServerControl() throws RemoteException{
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbClass = "com.mysql.jdbc.Driver";
        try {
            Class.forName(dbClass);
            conn = DriverManager.getConnection(dbUrl, "root", "123456789");
            registry = LocateRegistry.createRegistry(serverPort);
            registry.rebind(rmiService, this);
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String checkLogin(User user) throws RemoteException {
        String res = "false";
        String query = "Select * FROM user WHERE username ='"
                + user.getUsername() + "' AND password ='" + user.getPassword() + "'";
        System.out.println(query);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return "ok";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
}
