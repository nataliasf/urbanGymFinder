package com.example.urbangymfinder;

import java.util.ArrayList;

public class User {

    private String name;
    private String password;
    private ArrayList<Spot> favorites;

    public ArrayList<Spot> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Spot> favorites) {
        this.favorites = favorites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addFavorite(Spot spot) {
        this.favorites.add(spot);
    }


}
