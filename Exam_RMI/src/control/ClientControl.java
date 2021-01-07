/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import model.Student;

/**
 *
 * @author The Godfather
 */
public class ClientControl {
    
    private String rmiService = "rmiService";
    private StudentInterface studentImpl;
    
    public ClientControl() throws RemoteException, NotBoundException, MalformedURLException{
        studentImpl = (StudentInterface)Naming.lookup(rmiService);
    }
    
    public List<Student> search(String keyword) throws RemoteException{
        return studentImpl.search(keyword);
    }
    
    public boolean editStudent(int id, Student student) throws RemoteException{
        return studentImpl.editStudent(id, student);
    }
    
}
