package com.example.jfs_proyecto.Pojos;

public class Comentario {
    private String comentario;
    private String calificacion;
    private String id;
    private int estado;

    public Comentario() {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.id = id;
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
