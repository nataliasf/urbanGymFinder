package com.example.urbangymfinder;

public class Review {

    private String opinio;
    private Integer valoracio;
    private String id;

    public Review(String opinio, Integer valoracio) {
        this.opinio = opinio;
        this.valoracio = valoracio;
    }

    public String getOpinio() {
        return opinio;
    }

    public void setOpinio(String opinio) {
        this.opinio = opinio;
    }

    public Integer getValoracio() {
        return valoracio;
    }

    public void setValoracio(Integer valoracio) {
        this.valoracio = valoracio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
