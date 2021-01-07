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
public class Film implements Serializable{
    
    private int id;
    private String name;
    private int runtime;
    private float rating;

    public Film() {
    }

    public Film(int id, String name, int runtime, float rating) {
        this.id = id;
        this.name = name;
        this.runtime = runtime;
        this.rating = rating;
    }

    public Film(String name, int runtime, float rating) {
        this.name = name;
        this.runtime = runtime;
        this.rating = rating;
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

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Film{" + "id=" + id + ", name=" + name + ", runtime=" + runtime + ", rating=" + rating + '}';
    }
    
    public Object[] toObject(){
        return new Object[] {id, name, runtime, rating};
    }
    
} 
