/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ServerControl;
import java.rmi.RemoteException;

/**
 *
 * @author The Godfather
 */
public class ServerView {
    public ServerView() throws RemoteException{
        new ServerControl();
        System.out.println("Server is running!");
    }
}
