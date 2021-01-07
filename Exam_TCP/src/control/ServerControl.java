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
import model.Student;

/**
 *
 * @author The Godfather
 */
public class ServerControl {
    
    private int serverPort = 6666;
    private Dao dao;
    private ServerSocket myServer;
    
    public ServerControl(){
        dao = new Dao();
        try {
            myServer = new ServerSocket(serverPort);
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){
            listening();
        }
    }
    
    public void listening(){
        try {
            Socket clientSocket = myServer.accept();
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            Object o = ois.readObject();
            if(o instanceof String){
                String keyword = (String)o;
                List<Student> list = dao.search(keyword);
                oos.writeObject(list);
                oos.flush();
            }
            else{
                Student student = (Student)o;
                boolean edit = dao.editStudent(student.getStudentId(), student);
                oos.writeObject(edit);
                oos.flush();
            }
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
//    public Student receiveData(){
//        try {
//            Socket clientSocket = myServer.accept();
//            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
//            Student = (Student)
//        } catch (IOException ex) {
//            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public void sendData(String str){
//        
//    }
}
