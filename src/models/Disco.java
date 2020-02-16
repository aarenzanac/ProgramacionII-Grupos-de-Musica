/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author alex
 */
public class Disco{
    private String nombreGrupo;
    private String titulo;
    private String estilo;
    private int numeroCanciones;
    private int añoPublicacion;

    public Disco(String nombreGrupo, String titulo, String estilo, int numeroCanciones, int añoPublicacion) {
        this.nombreGrupo = nombreGrupo;
        this.titulo = titulo;
        this.estilo = estilo;
        this.numeroCanciones = numeroCanciones;
        this.añoPublicacion = añoPublicacion;
    }
    
    public Disco(){
    
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEstilo() {
        return estilo;
    }

    public int getNumeroCanciones() {
        return numeroCanciones;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public void setNumeroCanciones(int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    public void setAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    @Override
    public String toString() {
        return "Disco{" + "Nombre del Grupo: " + nombreGrupo + " ---- Título: " + titulo + " ---- Estilo: " + estilo + " ---- Numero de Canciones: " + numeroCanciones + " ---- Año de Publicacion: " + añoPublicacion + '}';
    }
    
    
}
