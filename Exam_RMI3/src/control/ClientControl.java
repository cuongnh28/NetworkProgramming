/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Film;

/**
 *
 * @author The Godfather
 */
public class ClientControl {
    
    private FilmInterface filmInterface;
    private String rmiService = "rmiService";
    
    public ClientControl(){
        try {
            filmInterface = (FilmInterface)Naming.lookup(rmiService);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Film> selectAll(){
        List<Film> list = new ArrayList<>();
        try {
            list = filmInterface.selectAll();
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean addFilm(Film film){
        boolean check = false;
        try {
            check = filmInterface.addFilm(film);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    public boolean editFilm(Film film){
        boolean check = false;
        try {
            check = filmInterface.editFilm(film);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
}
