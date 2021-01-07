/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Film;

/**
 *
 * @author The Godfather
 */
public interface FilmInterface extends Remote{
    
    public boolean addFilm(Film film) throws RemoteException;
    public boolean editFilm(Film film) throws RemoteException;
    public List<Film> selectAll() throws RemoteException;
}
