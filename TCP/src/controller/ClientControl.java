/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author The Godfather
 */
public class ClientControl {
    private int port = 6666;
    private Socket myServer;
    private String host = "192.168.8.106";
    public ClientControl(){
    }
    
    public void openConnection(){
        try {
            myServer = new Socket(host, port);
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendData(User user){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(myServer.getOutputStream());
            oos.writeObject(user);
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String recieveData(){
        String res = "false";
        try {
            ObjectInputStream ois = new ObjectInputStream(myServer.getInputStream());
            Object o = ois.readObject();
            if(o instanceof String){
                res = (String)o;
            }
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
