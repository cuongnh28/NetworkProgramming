/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Film;
/**
 *
 * @author The Godfather
 */
public class FilmImpl extends UnicastRemoteObject implements FilmInterface{

    private Connection conn;
    private Statement stmt;
    
    public FilmImpl() throws RemoteException{
        String dbUrl = "jdbc:mysql://localhost:3306/exam";
        String dbClass = "com.mysql.jdbc.Driver";
        try {
            Class.forName(dbClass);
            conn = DriverManager.getConnection(dbUrl, "root", "123456789");
            stmt = conn.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(FilmImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean addFilm(Film film) throws RemoteException {
        String sql = "INSERT INTO film(name, runtime, rating) VALUES ('" + film.getName() + "',"
                + film.getRuntime() + "," + film.getRating() + ")";
        try {
            if(stmt.executeUpdate(sql) != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(FilmImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean editFilm(Film film) throws RemoteException {
        String sql = "UPDATE film SET name='" + film.getName() + "', runtime=" + film.getRuntime() 
                + ", rating=" + film.getRating() + "WHERE id=" + film.getId();
        try {
            if(stmt.executeUpdate(sql) != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(FilmImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Film> selectAll() throws RemoteException {
        List<Film> list = new ArrayList<>();
        String sql = "SELECT * FROM film";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Film film = new Film(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
                list.add(film);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
