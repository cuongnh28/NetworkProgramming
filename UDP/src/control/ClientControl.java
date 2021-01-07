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
import model.User;

/**
 *
 * @author The Godfather
 */
public class ClientControl {
    private int serverPort = 6666;
    private int clientPort = 5555;
    DatagramSocket myServer;
    DatagramPacket sendPacket, receivePacket;
    private String serverHost = "DESKTOP-QVLAHCS";
    
    public ClientControl(){    
    }
    
    public void openConnection(){
        try {
            myServer = new DatagramSocket(clientPort);
        } catch (SocketException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendData(User user){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(user);
            oos.flush();
            InetAddress IPAddress = InetAddress.getByName(serverHost);
            byte[] sendData = baos.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
            myServer.send(sendPacket);
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String receiveData(){
        String res = "";
        byte[] receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            myServer.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            res = (String)ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }   
}
