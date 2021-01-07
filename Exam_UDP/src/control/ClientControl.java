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
import model.Student;

/**
 *
 * @author The Godfather
 */
public class ClientControl {
    
    private DatagramPacket sendPacket, receivePacket;
    private DatagramSocket myClient;
    private int serverPort = 6666;
    private int clientPort = 5555;
    private String serverHost = "localhost";
    
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
    
    public void sendKeyword(String keyword){
        try {
            ByteArrayOutputStream bais = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bais);
            oos.writeObject(keyword);
            
            InetAddress IPAddress = InetAddress.getByName(serverHost);
            byte[] sendData = bais.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
            myClient.send(sendPacket);
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendStudent(Student student){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(student);
            oos.flush();
            
            byte[] sendData = baos.toByteArray();
            InetAddress IPAddress = InetAddress.getByName(serverHost);
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
            myClient.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public List<Student> receiveStudent(){
        List<Student> list = new ArrayList<>();
        try {
            byte[] receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            myClient.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            list = (List<Student>)ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean receiveUpdate(){
        byte[] receiveData = new byte[1024];
        boolean check = false;
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
