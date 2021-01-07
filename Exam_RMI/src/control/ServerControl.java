/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.Student;

/**
 *
 * @author The Godfather
 */
public class ServerControl extends UnicastRemoteObject{
    
    private StudentImpl studentImpl;
    private String rmiService = "rmiService";
    
    public ServerControl() throws RemoteException, MalformedURLException{
        studentImpl = new StudentImpl();
        LocateRegistry.createRegistry(1099);
        Naming.rebind(rmiService, studentImpl);
    }
    
}
