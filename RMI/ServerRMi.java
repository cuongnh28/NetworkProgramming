/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author HoangHung
 */
public class ServerRMi {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        CalCulatorimpl calCulatorimpl=new CalCulatorimpl();
        LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://localhost/cal", calCulatorimpl);
    }
}
