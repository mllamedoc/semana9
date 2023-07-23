package com.example.semana9;

public class Postulante {

    int id;
    String nombre;
    String apellido;
    String universidad;


    public Postulante(){

    }

    public Postulante(String nombre, String apellido, String universidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.universidad = universidad;
    }

    public Postulante(int id, String nombre, String apellido, String universidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.universidad = universidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }
}

