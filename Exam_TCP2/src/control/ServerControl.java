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
public class ServerControl {
    
    private String serverHost = "localhost";
    private Dao dao;
    private ServerSocket myServer;
    private Socket clientSocket;
    private int port = 6666;
    
    public ServerControl(){
        dao = new Dao();
        try {
            myServer = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true)
            listening();
    }
    
    public void listening(){
        Object o = receiveData();
        if(o instanceof String){
            boolean check = dao.checkLogin((String)o);
            sendData(check);
        }
        else{
            boolean addUser = dao.addUser((User)o);
            sendData(addUser);
        }
    }
    
    public Object receiveData(){
        Object o = new Object();
        try {
            clientSocket = myServer.accept();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            o = ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }
    
    public void sendData(Object o){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.writeObject(o);
            oos.flush();
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
