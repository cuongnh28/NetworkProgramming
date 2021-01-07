/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import java.rmi.RemoteException;
import view.ServerView;

/**
 *
 * @author The Godfather
 */
public class ServerRun {
    public static void main(String[] args) throws RemoteException {
        ServerView view = new ServerView();
    }
}
