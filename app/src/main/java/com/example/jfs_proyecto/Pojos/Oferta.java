package com.example.jfs_proyecto.Pojos;

public class Oferta {
    private String nombre, id, puesto, profesion, sueldo, edad, estatura, nacionalidad, estado, segundo, tercer, discapacidades, estudios;

    public Oferta(String nombre, String id, String puesto, String profesion, String sueldo, String edad, String estatura, String nacionalidad, String estado, String segundo, String tercer, String discapacidades, String estudios) {
        this.nombre = nombre;
        this.id = id;
        this.puesto = puesto;
        this.profesion = profesion;
        this.sueldo = sueldo;
        this.edad = edad;
        this.estatura = estatura;
        this.nacionalidad = nacionalidad;
        this.estado = estado;
        this.segundo = segundo;
        this.tercer = tercer;
        this.discapacidades = discapacidades;
        this.estudios = estudios;
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSegundo() {
        return segundo;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }

    public String getTercer() {
        return tercer;
    }

    public void setTercer(String tercer) {
        this.tercer = tercer;
    }

    public String getDiscapacidades() {
        return discapacidades;
    }

    public void setDiscapacidades(String discapacidades) {
        this.discapacidades = discapacidades;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }
}
