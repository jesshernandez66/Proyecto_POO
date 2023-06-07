package org.example.Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaInsecto extends JFrame {
    //Necesitamos 6 etiquetas y 6 Jtextfield
    private JLabel lblID;
    private JLabel lblNombre;
    private JLabel lblTipo;
    private JLabel lblTamano;
    private JLabel lblAlimentacion;
    private JLabel lblUrl;
    private JTextField txtID;
    private JTextField txtNombre;
    private JTextField txtTipo;
    private JTextField txtTamano;
    private JTextField txtAlimentacion;
    private JTextField txtUrl;
    //necesitamos 1 boton, tabla, layaut, 4 paneles
    private JButton botonAdd;
    private JTable tabla;
    private JScrollPane scroll;
    private GridLayout layout;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel imagenInsecto;
    private JButton btnCargar;
    // Panle 4
    private JLabel lblIDA;
    private JLabel lblNombreA;
    private JLabel lblTipoA;
    private JLabel lblTamanoA;
    private JLabel lblAlimentacionA;
    private JLabel lblUrlA;
    private JTextField txtIDA;
    private JTextField txtNombreA;
    private JTextField txtTipoA;
    private JTextField txtTamanoA;
    private JTextField txtAlimentacionA;
    private JTextField txtUrlA;
    private JButton btnActualiza;
    private JLabel Eliminar;
    private JLabel lblElminarID;
    private JTextField txtEliminarID;
    private JButton BtnEliminar;
    private JLabel agregar;
    private JLabel Actualizar;

    //Metodos constructores para diseñar la vista
    public VentanaInsecto(String title) throws HeadlessException {
        super(title);
        this.setSize(800,800);
        layout = new GridLayout(2,2);
        //Agregamos el layout al contenedor principal
        this.getContentPane().setLayout(layout);

        //Configuramos cada panel
        //Panel 1
        panel1 = new JPanel(new FlowLayout());
        panel1.setBackground(new Color (126, 76, 203, 255));
        //Aquí ya estamos configurando las etiquetas y textos
        lblID = new JLabel("ID: ");
        lblNombre = new JLabel("Nombre: ");
        lblTipo = new JLabel("Tipo: ");
        lblTamano = new JLabel("Tama\u00f1o en centimetros: ");
        lblAlimentacion = new JLabel("Alimentaci\u00f3n ");
        lblUrl = new JLabel("URL de la foto: ");
        txtID = new JTextField(5);
        txtID.setText("0");
        txtID.setEnabled(false);
        txtNombre = new JTextField(5);
        txtTipo = new JTextField(5);
        txtTamano = new JTextField(5);
        txtAlimentacion = new JTextField(5);
        txtUrl = new JTextField(30);
        agregar=new JLabel("                                                     Agregar                                                      ");
        //vamos a agregar al panel 1
        panel1.add(agregar);
        panel1.add(lblID);
        panel1.add(txtID);
        panel1.add(lblNombre);
        panel1.add(txtNombre);
        panel1.add(lblTipo);
        panel1.add(txtTipo);
        panel1.add(lblTamano);
        panel1.add(txtTamano);
        panel1.add(lblAlimentacion);
        panel1.add(txtAlimentacion);
        panel1.add(lblUrl);
        panel1.add(txtUrl);
        //Agregamos el boton al panel1
        botonAdd = new JButton("Agregar");
        panel1.add(botonAdd);

        //Panel 2
        panel2 = new JPanel(new FlowLayout());
        panel2.setBackground(new Color (47, 213, 243, 236));
        btnCargar=new JButton("Cargar");
        panel2.add(btnCargar);
        //Vamos a agregar la tabla
        tabla = new JTable();
        scroll = new JScrollPane(tabla);
        panel2.add(scroll);


        //Panel 3
        panel3 = new JPanel(new FlowLayout());
        panel3.setBackground(new Color (234, 189, 72, 207));
        //Agregamos la imagen
        imagenInsecto = new JLabel("...");
        panel3.add(imagenInsecto);

        //Panel 4
        panel4 = new JPanel(new FlowLayout());
        panel4.setBackground(new Color (250, 50, 223, 239));
        Actualizar=new JLabel("                                                     Actualizar                                                      ");
        lblIDA = new JLabel("ID: ");
        lblNombreA = new JLabel("Nombre: ");
        lblTipoA = new JLabel("Tipo: ");
        lblTamanoA = new JLabel("Tama\u00f1o en centimetros: ");
        lblAlimentacionA = new JLabel("Alimentaci\u00f3n ");
        lblUrlA = new JLabel("URL de la foto: ");
        txtIDA= new JTextField(5);
        txtNombreA = new JTextField(5);
        txtTipoA = new JTextField(5);
        txtTamanoA = new JTextField(5);
        txtAlimentacionA = new JTextField(5);
        txtUrlA = new JTextField(30);
        btnActualiza=new JButton("Actualizar");
        Eliminar=new JLabel("                                                     Eliminar                                                      ");
        lblElminarID=new JLabel("ID: ");
        txtEliminarID=new JTextField(5);
        BtnEliminar=new JButton("Eliminar");
        panel4.add(Actualizar);
        panel4.add(lblIDA);
        panel4.add(txtIDA);
        panel4.add(lblNombreA);
        panel4.add(txtNombreA);
        panel4.add(lblTipoA);
        panel4.add(txtTipoA);
        panel4.add(lblTamanoA);
        panel4.add(txtTamanoA);
        panel4.add(lblAlimentacionA);
        panel4.add(txtAlimentacionA);
        panel4.add(lblUrlA);
        panel4.add(txtUrlA);
        panel4.add(btnActualiza);
        panel4.add(Eliminar);
        panel4.add(lblElminarID);
        panel4.add(txtEliminarID);
        panel4.add(BtnEliminar);
        // Agregamos los paneles ya configurados, y en qué posición están
        this.getContentPane().add(panel1,0);
        this.getContentPane().add(panel2,1);
        this.getContentPane().add(panel3,2);
        this.getContentPane().add(panel4,3);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public JLabel getLblID() {
        return lblID;
    }

    public void setLblID(JLabel lblID) {
        this.lblID = lblID;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JLabel getLblTipo() {
        return lblTipo;
    }

    public void setLblTipo(JLabel lblTipo) {
        this.lblTipo = lblTipo;
    }

    public JLabel getLblTamano() {
        return lblTamano;
    }

    public void setLblTamano(JLabel lblTamano) {
        this.lblTamano = lblTamano;
    }

    public JLabel getLblAlimentacion() {
        return lblAlimentacion;
    }

    public void setLblAlimentacion(JLabel lblAlimentacion) {
        this.lblAlimentacion = lblAlimentacion;
    }

    public JLabel getLblUrl() {
        return lblUrl;
    }

    public void setLblUrl(JLabel lblUrl) {
        this.lblUrl = lblUrl;
    }

    public JTextField getTxtID() {
        return txtID;
    }

    public void setTxtID(JTextField txtID) {
        this.txtID = txtID;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtTipo() {
        return txtTipo;
    }

    public void setTxtTipo(JTextField txtTipo) {
        this.txtTipo = txtTipo;
    }

    public JTextField getTxtTamano() {
        return txtTamano;
    }

    public void setTxtTamano(JTextField txtTamano) {
        this.txtTamano = txtTamano;
    }

    public JTextField getTxtAlimentacion() {
        return txtAlimentacion;
    }

    public void setTxtAlimentacion(JTextField txtAlimentacion) {
        this.txtAlimentacion = txtAlimentacion;
    }

    public JTextField getTxtUrl() {
        return txtUrl;
    }

    public void setTxtUrl(JTextField txtUrl) {
        this.txtUrl = txtUrl;
    }

    public JButton getBotonAdd() {
        return botonAdd;
    }

    public void setBotonAdd(JButton botonAdd) {
        this.botonAdd = botonAdd;
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }

    @Override
    public GridLayout getLayout() {
        return layout;
    }

    public void setLayout(GridLayout layout) {
        this.layout = layout;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JPanel getPanel2() {
        return panel2;
    }

    public void setPanel2(JPanel panel2) {
        this.panel2 = panel2;
    }

    public JPanel getPanel3() {
        return panel3;
    }

    public void setPanel3(JPanel panel3) {
        this.panel3 = panel3;
    }

    public JPanel getPanel4() {
        return panel4;
    }

    public void setPanel4(JPanel panel4) {
        this.panel4 = panel4;
    }

    public JLabel getImagenInsecto() {
        return imagenInsecto;
    }

    public void setImagenInsecto(JLabel imagenInsecto) {
        this.imagenInsecto = imagenInsecto;
    }

    public JLabel getLblIDA() {
        return lblIDA;
    }

    public void setLblIDA(JLabel lblIDA) {
        this.lblIDA = lblIDA;
    }

    public JLabel getLblNombreA() {
        return lblNombreA;
    }

    public void setLblNombreA(JLabel lblNombreA) {
        this.lblNombreA = lblNombreA;
    }

    public JLabel getLblTipoA() {
        return lblTipoA;
    }

    public void setLblTipoA(JLabel lblTipoA) {
        this.lblTipoA = lblTipoA;
    }

    public JLabel getLblTamanoA() {
        return lblTamanoA;
    }

    public void setLblTamanoA(JLabel lblTamanoA) {
        this.lblTamanoA = lblTamanoA;
    }

    public JLabel getLblAlimentacionA() {
        return lblAlimentacionA;
    }

    public void setLblAlimentacionA(JLabel lblAlimentacionA) {
        this.lblAlimentacionA = lblAlimentacionA;
    }

    public JLabel getLblUrlA() {
        return lblUrlA;
    }

    public void setLblUrlA(JLabel lblUrlA) {
        this.lblUrlA = lblUrlA;
    }

    public JTextField getTxtIDA() {
        return txtIDA;
    }

    public void setTxtIDA(JTextField txtIDA) {
        this.txtIDA = txtIDA;
    }

    public JTextField getTxtNombreA() {
        return txtNombreA;
    }

    public void setTxtNombreA(JTextField txtNombreA) {
        this.txtNombreA = txtNombreA;
    }

    public JTextField getTxtTipoA() {
        return txtTipoA;
    }

    public void setTxtTipoA(JTextField txtTipoA) {
        this.txtTipoA = txtTipoA;
    }

    public JTextField getTxtTamanoA() {
        return txtTamanoA;
    }

    public void setTxtTamanoA(JTextField txtTamanoA) {
        this.txtTamanoA = txtTamanoA;
    }

    public JTextField getTxtAlimentacionA() {
        return txtAlimentacionA;
    }

    public void setTxtAlimentacionA(JTextField txtAlimentacionA) {
        this.txtAlimentacionA = txtAlimentacionA;
    }

    public JTextField getTxtUrlA() {
        return txtUrlA;
    }

    public void setTxtUrlA(JTextField txtUrlA) {
        this.txtUrlA = txtUrlA;
    }

    public JButton getBtnActualiza() {
        return btnActualiza;
    }

    public void setBtnActualiza(JButton btnActualiza) {
        this.btnActualiza = btnActualiza;
    }

    public JButton getBtnCargar() {
        return btnCargar;
    }

    public void setBtnCargar(JButton btnCargar) {
        this.btnCargar = btnCargar;
    }

    public JLabel getEliminar() {
        return Eliminar;
    }

    public void setEliminar(JLabel eliminar) {
        Eliminar = eliminar;
    }

    public JLabel getLblElminarID() {
        return lblElminarID;
    }

    public void setLblElminarID(JLabel lblElminarID) {
        this.lblElminarID = lblElminarID;
    }

    public JTextField getTxtEliminarID() {
        return txtEliminarID;
    }

    public void setTxtEliminarID(JTextField eliminarID) {
        txtEliminarID = eliminarID;
    }

    public JButton getBtnEliminar() {
        return BtnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        BtnEliminar = btnEliminar;
    }
    public void limpiar4(){
        txtNombreA.setText("");
        txtTipoA.setText("");
        txtTamanoA.setText("");
        txtAlimentacionA.setText("");
        txtUrlA.setText("");
        txtIDA.setText("");
        txtEliminarID.setText("");
    }

    public void limpiar1(){
        txtNombre.setText("");
        txtTipo.setText("");
        txtTamano.setText("");
        txtAlimentacion.setText("");
        txtUrl.setText("");
        txtID.setText("");
        txtEliminarID.setText("");
    }
}
