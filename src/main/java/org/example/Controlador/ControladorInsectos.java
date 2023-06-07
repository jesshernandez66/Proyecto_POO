package org.example.Controlador;

import org.example.Modelo.Insectos;
import org.example.Modelo.ModeloTablaInsectos;
import org.example.Persistencia.ConexionSingleton;
import org.example.Vista.VentanaInsecto;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorInsectos extends MouseAdapter {
    //creamos el objeto ventana, llamado view
    private VentanaInsecto view;
    //Objeto modelo
    private ModeloTablaInsectos modelo;
    public ControladorInsectos(VentanaInsecto view) {
        this.view = view;
        //Vamos a crear los eventos de la tabla, el boton agregar y que al presionar la tabla muestre la foto, del tipo mousecliked
        //Son los listeners para los botones
        this.view.getBotonAdd().addMouseListener(this);
        this.view.getTabla().addMouseListener(this);
        modelo = new ModeloTablaInsectos();
        this.view.getTabla().setModel(modelo);
        this.view.getBtnCargar().addMouseListener(this);
        this.view.getBtnActualiza().addMouseListener(this);
        this.view.getBtnEliminar().addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
//Agregamos los eventos, con el if identifico q se está apachurrando jaja
        if (e.getSource() == this.view.getBtnCargar()) {
         //Actualizar tabla
            modelo.cargardatos();
            this.view.getTabla().setModel(modelo);
            //con update
            this.view.getTabla().updateUI();
        }
        //si el evento viene desde boton add
        if(e.getSource()== view.getBotonAdd()){
//Rellenamos los campos de texto y se mandan los valores que se obtienen de la tabla a un insecto nuevo (inso)
            Insectos inso = new Insectos();
            //ID incrementa solo
            inso.setID(0);
            inso.setNombre(this.view.getTxtNombre().getText());
            inso.setTipo(this.view.getTxtTipo().getText());
            inso.setTamano(this.view.getTxtTamano().getText());
            inso.setAlimentacion(this.view.getTxtAlimentacion().getText());
            inso.setUrl(this.view.getTxtUrl().getText());
            this.view.getTabla().updateUI();
            //True si se pudo agregar,  manda el texto al usuario
            if (modelo.agregainso(inso)){
                JOptionPane.showMessageDialog(view, "Se agrego correctamente", "aviso", JOptionPane.INFORMATION_MESSAGE);
                this.view.getTabla().updateUI();
            }else {
                JOptionPane.showMessageDialog(view, "No se pudo agregar", "aviso", JOptionPane.ERROR_MESSAGE);

            }
     
            this.view.limpiar1();
            this.view.getTabla().updateUI();
        }
        if (e.getSource() == this.view.getBtnActualiza()) {
            //Int que hace pregunta de si quieres actualizar, si presionas si regresa valor de 0
            int respuesta = JOptionPane.showConfirmDialog(view, "Estas seguro que se actualice el registro?",
                    "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (respuesta==0){
                //Se actualiza, metiendo el ID 
                Insectos inso = new Insectos();
                inso.setID(Integer.parseInt((String) this.view.getTxtIDA().getText()));
                inso.setNombre(this.view.getTxtNombreA().getText());
                inso.setTipo(this.view.getTxtTipoA().getText());
                inso.setTamano(this.view.getTxtTamanoA().getText());
                inso.setAlimentacion(this.view.getTxtAlimentacionA().getText());
                inso.setUrl(this.view.getTxtUrlA().getText());
//
                if (modelo.actualizar(inso)) {
                    JOptionPane.showMessageDialog(view, "Se actualizo correctamente", "aviso", JOptionPane.INFORMATION_MESSAGE);
                    this.view.getTabla().updateUI();
                } else {
                    JOptionPane.showMessageDialog(view, "No se pudo actualizo en la base de datos. por favor revise su conexion", "ErroR", JOptionPane.ERROR_MESSAGE);

                }
                   //Limpia los textos del panel, manda una cadena vacía y lo limpia
                this.view.limpiar4();
            }

        }
        //el boton de eliminar hace todo, porque no pude como el profe
        if (e.getSource() == this.view.getBtnEliminar()) {
            int respuesta = JOptionPane.showConfirmDialog(view, "Estas seguro de borrar el registro?",
                    "Confirmacion", JOptionPane.YES_NO_OPTION);
            //Si la respuesta es 0, se elimina
            if (respuesta == 0) {
                String sqlDelete = "DELETE FROM Insectos WHERE ID=?;";
                PreparedStatement pstm = null;
                System.out.println("boton eliminar");
                try {
                    pstm = ConexionSingleton.getInstance("insectos.db").getConnection().prepareStatement(sqlDelete);
                } catch (SQLException ex) {
                    System.out.println("no hubo conexion");;
                }
                try {
                    pstm.setInt(1, Integer.parseInt(this.view.getTxtEliminarID().getText()));
                    this.view.getTabla().updateUI();
                    JOptionPane.showMessageDialog(view, "Se elimino correctamente", "aviso", JOptionPane.INFORMATION_MESSAGE);
              //Con una excepcion Si no se puede eliminar por poner una letra en lugar de un numero, manda un aviso
                } catch (NumberFormatException a) {
                    JOptionPane.showMessageDialog(view, "No se elimino correctamente", "aviso", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    System.out.println("no se pudo conectar"+ex.getMessage());
                }
                try {
                    pstm.executeUpdate();
                    //Excepcion porque no se encuentra el id,  si pones 10 y solo tengo 5
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Error en Id", "aviso", JOptionPane.INFORMATION_MESSAGE);
                    throw new RuntimeException(ex);
                    //pase lo que pase, se va a limpiar el ID
                }finally {
                    this.view.limpiar4();
                }}

        }
        //si el evento viene desde la tabla
        if(e.getSource()== view.getTabla()){
            System.out.println("Evento sobre tabla ");
            //Averiguamos que elemento de la tabla esta selecionado con getSelectedRow
            int index = this.view.getTabla().getSelectedRow();
            //pedimos que nos regrese, con un objeto temporal
            Insectos tmp =modelo.getInsectoAtIndex(index);
            try{
                this.view.getImagenInsecto().setIcon(tmp.getImagen());
                this.view.getImagenInsecto().setText("");
            }catch ( MalformedURLException mfue){
                System.out.println(e.toString());
            }

        }

    }
}
