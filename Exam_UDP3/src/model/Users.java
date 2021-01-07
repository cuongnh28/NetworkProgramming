/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author The Godfather
 */
public class Users implements Serializable{
    
    private int id;
    private String username, password, name, address;

    public Users() {
    }

    public Users(int id, String username, String password, String name, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", address=" + address + '}';
    }
    
    public Object[] toObject(){
        return new Object[] {id,username, password, name, address};
    }
      
}
