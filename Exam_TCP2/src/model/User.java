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
public class User implements Serializable{
    private int id;
    private String name, birth, address;

    public User() {
    }

    public User(String name, String birth, String address) {
        this.name = name;
        this.birth = birth;
        this.address = address;
    }
    
    public User(int id, String name, String birth, String address) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", birth=" + birth + ", address=" + address + '}';
    }
    
}
