package org.example;

import org.example.Controlador.ControladorInsectos;
import org.example.Vista.VentanaInsecto;

public class Main {
    public static void main(String[] args) {
        //Creamos el objeto de tipo insecto para que se ejecute la tabla
        VentanaInsecto view = new VentanaInsecto("Proyecto Final"); 
        //Vamos a meter el controlador en la tabla
        ControladorInsectos controller=new ControladorInsectos(view);
    }
}