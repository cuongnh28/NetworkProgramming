/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author The Godfather
 */
public class ClientControl {
    
    private String serverHost = "localhost";
    private Socket clientSocket, serverSocket;
    private int serverPort = 6666;
    
    public ClientControl(){
            clientSocket = new Socket();
    }
    
    public void close(){
        try {
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendLogin(String name){
        try {
            serverSocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(serverSocket.getOutputStream());
            oos.writeObject(name);
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendAddUser(User user){
        try {
            serverSocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(serverSocket.getOutputStream());
            oos.writeObject(user);
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean receiveData(){
        try {
            ObjectInputStream ois = new ObjectInputStream(serverSocket.getInputStream());
            return (boolean)ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
