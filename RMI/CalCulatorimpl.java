/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author HoangHung
 */
public class CalCulatorimpl extends UnicastRemoteObject implements CalCulator{

    public CalCulatorimpl() throws RemoteException  {
    super();
    }
    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
    
}
