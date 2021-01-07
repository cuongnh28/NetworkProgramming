/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author HoangHung
 */
public class ServerObj {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        ProductImpl productImpl=new ProductImpl();
        LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://localhost/Product", productImpl);
    }
}
