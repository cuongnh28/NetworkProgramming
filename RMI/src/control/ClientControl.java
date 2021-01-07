/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import model.User;

/**
 *
 * @author The Godfather
 */
public class ClientControl {
    
    private LoginInterface rmiServer;
    private int serverPort = 6666;
    private String serverHost = "DESKTOP-QVLAHCS";
    private Registry registry;
    private String rmiService = "rmiService";
    
    public ClientControl() throws RemoteException, NotBoundException{
        registry = LocateRegistry.getRegistry(serverHost, serverPort);
        rmiServer = (LoginInterface) registry.lookup(rmiService);
    }
    
    public String check(User user) throws RemoteException{
        String res = null;
        res = rmiServer.checkLogin(user);
        return res;
    }
   
}
