package org.example.Modelo;
import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Insectos {
    //Vamos a poner sus campos por capturar
    private int ID;
    private String nombre;
    private String tipo;
    private String tamano;
    private String alimentacion;
    private String url;

    public Insectos() {
    }

    public Insectos(int ID, String nombre, String tipo, String tamano, String alimentacion, String url) {
        this.ID = ID;
        this.nombre = nombre;
        this.tipo = tipo;
        this.tamano = tamano;
        this.alimentacion = alimentacion;
        this.url = url;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(String alimentacion) {
        this.alimentacion = alimentacion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //vamos a levantar la imagen, con url y icon
    public ImageIcon getImagen() throws MalformedURLException {
        //arreglamos la excepcion
        URL urlImage = new URL(this.url);
        return new ImageIcon(urlImage);
    }
}