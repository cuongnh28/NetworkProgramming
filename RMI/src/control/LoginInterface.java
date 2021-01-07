/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.User;

/**
 *
 * @author The Godfather
 */
public interface LoginInterface extends Remote{
    public String checkLogin(User user) throws RemoteException;
}
