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
    
    private String rmiService = "rmiService";
    private UserInterface userInterface;
    
    public ClientControl(){
        try {
            userInterface = (UserInterface)Naming.lookup(rmiService);
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<User> loadAllUser(){
        List<User> list = new ArrayList<>();
        try {
            list = userInterface.selectAll();
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean deleteUser(int id){
        boolean check = false;
        try {
            check = userInterface.deleteUser(id);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
