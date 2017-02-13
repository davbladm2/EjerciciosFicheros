package com.example.dm2.ejerciciosficheros;


public class Webs {

    private String nombre;
    private String enlace;
    private String logo;
    private String identificador;

    public Webs(String nombre, String enlace, String logo, String identificador) {
        this.nombre=nombre;
        this.enlace=enlace;
        this.logo=logo;
        this.identificador=identificador;
    }

    public String getNombre() {
        return nombre;
    }
    public String getEnlace() {
        return enlace;
    }
    public String getIdentificador() {
        return identificador;
    }

    public String getLogo() {
        return logo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
