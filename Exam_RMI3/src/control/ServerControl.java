/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author The Godfather
 */
public class ServerControl {
    
    private FilmImpl filmImpl;
    private String rmiService = "rmiService";
    
    public ServerControl(){
        try {
            filmImpl = new FilmImpl();
            LocateRegistry.createRegistry(1099);
            Naming.rebind(rmiService, filmImpl);
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
