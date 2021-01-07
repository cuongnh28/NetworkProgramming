/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
public class ClientControl {
    
    private DatagramPacket sendPacket, receivePacket;
    private DatagramSocket clientSocket, serverSocket;
    private int clientPort = 5555,
                serverPort = 6666;
    private String serverHost = "localhost";
    
    public ClientControl(){
        try {
            clientSocket = new DatagramSocket(clientPort);
        } catch (SocketException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){
        clientSocket.close();
    }
    
    public void sendLogin(String info){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(info);
            oos.flush();
            byte[] sendData = baos.toByteArray();
            InetAddress IpAddress = InetAddress.getByName(serverHost);
            sendPacket = new DatagramPacket(sendData, sendData.length, IpAddress, serverPort);
            clientSocket.send(sendPacket);
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    
    public void sendEdit(Users user){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(user);
            oos.flush();
            byte[] sendData = baos.toByteArray();
            InetAddress IpAddress = InetAddress.getByName(serverHost);
            sendPacket = new DatagramPacket(sendData, sendData.length, IpAddress, serverPort);
            clientSocket.send(sendPacket);
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    
    public Object receiveCheckLogin(){
        byte[] receiveData = new byte[1024];
        Object o = new Object();
        try {
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            o = ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }
    
    public boolean receiveCheckEdit(){
        byte[] receiveData = new byte[1024];
        try {
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object o = ois.readObject();
            return (boolean)o;
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
