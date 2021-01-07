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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author The Godfather
 */
public class ClientControl {
    
    private String dbHost = "localhost";
    private Socket myClient;
    private int serverPort = 6666;
    
    public ClientControl(){
        try {
            myClient = new Socket(dbHost, serverPort);
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection(){
        try {
            myClient.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendKeyword(String keyword){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(myClient.getOutputStream());
            oos.writeObject(keyword);
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendStudent(Student student){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(myClient.getOutputStream());
            oos.writeObject(student);
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Student> receiveStudent(){
        List<Student> list = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(myClient.getInputStream());
            Object o = ois.readObject();
            list =  (List<Student>) o;
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean receiveEdit(){
        boolean res = false;
        try {
            ObjectInputStream ois = new ObjectInputStream(myClient.getInputStream());
            Object o = ois.readObject();
            res = (boolean)o;
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
}
