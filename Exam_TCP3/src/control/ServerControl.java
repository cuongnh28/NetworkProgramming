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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author The Godfather
 */
public class ServerControl {
    
    private ServerSocket myServer;
    private Socket clientSocket;
    Dao dao;
    
    public ServerControl(){
        try {
            myServer = new ServerSocket(6666);
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        dao = new Dao();
        while(true)
            listening();
    }
    
    public void listening(){
        Object o = receiveData();
        if(o instanceof String){
            List<User> list = dao.searchByName((String)o);
            sendData(list);
        }
        else{
            boolean check = dao.addUser((User)o);
            sendData(check);
        }
    }
    
    public Object receiveData(){
        Object o = new Object();
        try{
            clientSocket = myServer.accept();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            o = ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return o;
    }
    
    public void sendData(Object o){
        try {
            ObjectOutputStream ois = new ObjectOutputStream(clientSocket.getOutputStream());
            ois.writeObject(o);
            ois.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
