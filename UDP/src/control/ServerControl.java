/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
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
    private DatagramPacket receivePacket, sendPacket;
    private DatagramSocket myServer;
    private int severPort = 6666;
    public ServerControl(){
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbClass = "com.mysql.jdbc.Driver";
        try {
            Class.forName(dbClass);
            conn = DriverManager.getConnection(dbUrl, "root", "123456789");
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        openConnection(severPort);
        while(true){
            listening();
        }
    }
    
    public void openConnection(int port){
        try {
            myServer = new DatagramSocket(port);
        } catch (SocketException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listening(){
        String res = "false";
        User user = receiveData();
        if(checkLogin(user)){
            res = "ok";
        }
        sendData(res);
    }
    
    public User receiveData(){
        User user = null;
        byte[] receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            myServer.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            user = (User)ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public void sendData(String str){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(str);
            oos.flush();
            InetAddress IPAdress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            byte[] sendData = baos.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAdress, clientPort);
            myServer.send(sendPacket);
        }catch(Exception ex){
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    public boolean checkLogin(User user) {
        String query = "Select * FROM user WHERE username ='"
                + user.getUsername() + "' AND password ='" + user.getPassword() + "'";
        System.out.println(query);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
