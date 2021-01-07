/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author HoangHung
 */
public class ClientRMI {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        CalCulator cal=(CalCulator) Naming.lookup("rmi://localhost/cal");
        System.out.println(cal.add(5, 6));
    }
}
