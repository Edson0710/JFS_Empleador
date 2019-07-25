package com.example.jfs_proyecto.Pojos;

public class Oferta {
    private String nombre, id, puesto, profesion, sueldo, edad, estatura;

    public Oferta(String nombre, String id, String puesto, String profesion, String sueldo, String edad, String estatura) {
        this.nombre = nombre;
        this.id = id;
        this.puesto = puesto;
        this.profesion = profesion;
        this.sueldo = sueldo;
        this.edad = edad;
        this.estatura = estatura;
    }

    public Oferta() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }
}
