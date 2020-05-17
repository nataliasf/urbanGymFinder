package com.example.urbangymfinder;

import java.util.ArrayList;

public class User {

    private String email;
    private String nombre;
    private String password;
    private ArrayList<Spot> favoritespots;

    public User(String name, String email, String password){
        this.nombre = name;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Spot> getFavoritespots() {
        return favoritespots;
    }

    public void setFavoritespots(ArrayList<Spot> favoritespots) {
        this.favoritespots = favoritespots;
    }

    public void addFavorite(Spot favorite) {
        this.favoritespots.add(favorite);
    }
}
