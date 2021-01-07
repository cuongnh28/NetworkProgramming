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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author The Godfather
 */
public class ServerControl {
    
    private Dao dao;
    private DatagramPacket sendPacket, receivePacket;
    private DatagramSocket myServer;
    private int serverPort = 6666;
    
    public ServerControl(){
        dao = new Dao();
        try {
            myServer = new DatagramSocket(serverPort);
        } catch (SocketException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true)
            listening();
    }
    
    public void listening(){
        Object o = receiveData();
        if(o instanceof Integer){
            int id = (Integer)o;
            boolean checkDelete = dao.delete(id);
            sendData(checkDelete);
        }
        else{
            String name = (String)o;
            List<User> list = dao.searchByName(name);
            sendData(list);
        }
    }
    
    public Object receiveData(){
        Object o = new Object();
        try {
            byte[] receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            myServer.receive(receivePacket);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            o = ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }
    
    public void sendData(Object o){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.flush();
            
            InetAddress IpAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            byte[] sendData = baos.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, IpAddress, clientPort);
            myServer.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
