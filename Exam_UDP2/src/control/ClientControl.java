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
    
    private String serverHost = "localhost";
    private int serverPort = 6666,
                clientPort = 5555;
    private DatagramSocket myClient;
    private DatagramPacket sendPacket, receivePacket;
    
    public ClientControl(){
        try {
            myClient = new DatagramSocket(clientPort);
        } catch (SocketException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){
        myClient.close();
    }
    
    public void sendName(String name){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(name);
            byte[] sendData = baos.toByteArray();
            InetAddress IpAddress = InetAddress.getByName(serverHost);
            sendPacket = new DatagramPacket(sendData, sendData.length, IpAddress, serverPort);
            myClient.send(sendPacket);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void sendId(int id){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(id);
            byte[] sendData = baos.toByteArray();
            InetAddress IpAddress = InetAddress.getByName(serverHost);
            sendPacket = new DatagramPacket(sendData, sendData.length, IpAddress, serverPort);
            myClient.send(sendPacket);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public List<User> receiveUser(){
        List<User> list = new ArrayList<>();
        byte[] receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            myClient.receive(receivePacket);           
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            list = (List<User>)ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean receiveDelete(){
        boolean check = false;
        byte[] receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            myClient.receive(receivePacket);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            check = (boolean)ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
