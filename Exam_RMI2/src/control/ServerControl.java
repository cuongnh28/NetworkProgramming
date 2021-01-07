/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author The Godfather
 */
public class ServerControl {
    
    private Registry registry;
    private String rmiService = "rmiService";
    private UserImpl userImpl;
    
    public ServerControl(){
        try {
            userImpl = new UserImpl();
            LocateRegistry.createRegistry(1099);
            Naming.rebind(rmiService, userImpl);
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
