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
    //necesitamos declarar la vista, con su constructor
    private VentanaInsecto view;
    private ModeloTablaInsectos modelo;
    public ControladorInsectos(VentanaInsecto view) {
        this.view = view;
        //Vamos a crear los eventos de la tabla, el boton agregar y que al presionar la tabla muestre la foto, del tipo mousecliked
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

        if (e.getSource() == this.view.getBtnCargar()) {
            modelo.cargardatos();
            this.view.getTabla().setModel(modelo);
            this.view.getTabla().updateUI();
        }
        //si el evento viene desde boton add
        if(e.getSource()== view.getBotonAdd()){

            Insectos inso = new Insectos();
            inso.setID(0);
            inso.setNombre(this.view.getTxtNombre().getText());
            inso.setTipo(this.view.getTxtTipo().getText());
            inso.setTamano(this.view.getTxtTamano().getText());
            inso.setAlimentacion(this.view.getTxtAlimentacion().getText());
            inso.setUrl(this.view.getTxtUrl().getText());
            this.view.getTabla().updateUI();
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
            int respuesta = JOptionPane.showConfirmDialog(view, "Estas seguro que se actualice el registro?",
                    "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (respuesta==0){
                Insectos inso = new Insectos();
                inso.setID(Integer.parseInt((String) this.view.getTxtIDA().getText()));
                inso.setNombre(this.view.getTxtNombreA().getText());
                inso.setTipo(this.view.getTxtTipoA().getText());
                inso.setTamano(this.view.getTxtTamanoA().getText());
                inso.setAlimentacion(this.view.getTxtAlimentacionA().getText());
                inso.setUrl(this.view.getTxtUrlA().getText());

                if (modelo.actualizar(inso)) {
                    JOptionPane.showMessageDialog(view, "Se actualizo correctamente", "aviso", JOptionPane.INFORMATION_MESSAGE);
                    this.view.getTabla().updateUI();
                } else {
                    JOptionPane.showMessageDialog(view, "No se pudo actualizo en la base de datos. por favor revise su conexion", "ErroR", JOptionPane.ERROR_MESSAGE);

                }
                this.view.limpiar4();
            }

        }
        if (e.getSource() == this.view.getBtnEliminar()) {
            int respuesta = JOptionPane.showConfirmDialog(view, "Estas seguro de borrar el registro?",
                    "Confirmacion", JOptionPane.YES_NO_OPTION);
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
                } catch (NumberFormatException a) {
                    JOptionPane.showMessageDialog(view, "No se elimino correctamente", "aviso", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    System.out.println("no se pudo conectar"+ex.getMessage());
                }
                try {
                    pstm.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Error en Id", "aviso", JOptionPane.INFORMATION_MESSAGE);
                    throw new RuntimeException(ex);
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
