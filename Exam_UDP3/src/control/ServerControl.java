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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Users;

/**
 *
 * @author The Godfather
 */
public class ServerControl {
    
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket, sendPacket;
    private int serverPort = 6666;
    private Dao dao;
    
    
    public ServerControl(){
        try {
            serverSocket = new DatagramSocket(serverPort);
        } catch (SocketException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        dao = new Dao();
        while(true)
            listening();
    }
    
    public void listening(){
        Object o = receiveData();
        if(o instanceof String){
            String obj = (String)o;
            String[] tmp = obj.split(" ");
            Users user = new Users(tmp[0], tmp[1]);
            if(!dao.checkLogin(user))
                sendData(false);
            else{
                Users u = dao.loadUsers(tmp[0], tmp[1]);
                sendData(u);
            }
        }
        else{
            Users user = (Users)o;
            if(dao.editUser(user))
                sendData(true);
            else
                sendData(false);
        }
    }
    
    public Object receiveData(){
        Object o = new Object();
        try {
            byte[] receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            o = ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }
    
    public void sendData(Object o){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.flush();
            byte[] sendData = baos.toByteArray();
            InetAddress IpAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            sendPacket = new DatagramPacket(sendData, sendData.length, IpAddress, clientPort);
            serverSocket.send(sendPacket);
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
