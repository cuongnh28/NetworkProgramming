/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author The Godfather
 */
public class ClientControl {
    
    private Socket clientSocket, serverSocket;
    private int serverPort = 6666;
    private String serverHost = "localhost";
    
    public ClientControl(){
        try {
            clientSocket = new Socket();
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){
        try {
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendUser(User user){
        try {
            serverSocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(serverSocket.getOutputStream());
            oos.writeObject(user);
            oos.flush();
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendName(String name){
        try {
            serverSocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(serverSocket.getOutputStream());
            oos.writeObject(name);
            oos.flush();
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean receiveAddUser(){
        try {
            ObjectInputStream ois = new ObjectInputStream(serverSocket.getInputStream());
            Object o = ois.readObject();
            return(boolean)o;
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<User> receiveSearch(){
        List<User> list = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(serverSocket.getInputStream());
            Object o = ois.readObject();
            list = (List<User>)o;
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
