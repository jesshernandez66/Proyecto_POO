package org.example.Modelo;

import org.example.Persistencia.insectosDAO;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.ArrayList;

//IMplementamos tableModel
public class ModeloTablaInsectos implements TableModel {
    //Hacemos un arraylist que contenga elementos del tipo insecto, llamados datos
    private ArrayList<Insectos> datos;
    //Constructor por defecto inicializado
    public static final int COLS = 6;
    private insectosDAO ldao;
    public ModeloTablaInsectos() {
        ldao = new insectosDAO();
        datos = new ArrayList<>();
    }
    //Constructor sobrecargado ya instanciadoy con datos
    public ModeloTablaInsectos(ArrayList<Insectos> datos) {
        ldao = new insectosDAO();
        this.datos = datos;
    }
    //get row count lo necesita la vista para saber cuantos elementos o lineas va a pintar
    @Override
    public int getRowCount() {
        //Obtendra la info de datos.size
        return datos.size();
    }
    //get column count lo necesita la vista para saber cuantas columnas va a reservar para enseñar la info
    @Override
    public int getColumnCount() {
        //Haremos una constante COLS que muestre nuestras 6 columnas
        return COLS;

    }
    //Column name es para saber los nombres de cada elemento en cada titulo de las columnas
    @Override
    public String getColumnName(int columnIndex) {
        String nombreCol = " ";
        switch (columnIndex) {
            case 0:
                nombreCol= "ID";
                break;
            case 1:
                nombreCol= "Nombre";
                break;
            case 2:
                nombreCol= "Tipo";
                break;
            case 3:
                nombreCol= "Tama\u00f1o (cm)";
                break;
            case 4:
                nombreCol= "Alimentaci\u00f3n";
                break;
            case 5:
                nombreCol= "Foto";
                break;
            default:
                nombreCol = "";

        }

        //Si el string no capta nada, va a regresar una cadena vacía
        return nombreCol;
    }

    //get column class sirve para saber de que tipo será cada columna y así pintarlas
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            default:
                return String.class;
        }
    }
    //Sirve para saber si podremos editar las celdas
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //con true diremos que si son editables
        return true;
    }
    //daremos valor en cada posición (X,Y)
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Con lo siguiente obtendremos un objeto que esté en la posición que nos estén mandando
        Insectos tmp = datos.get(rowIndex);
        switch (columnIndex){
            case 0:
                return tmp.getID();
            case 1:
                return tmp.getNombre();
            case 2:
                return tmp.getTipo();
            case 3:
                return tmp.getTamano();
            case 4:
                return tmp.getAlimentacion();
            case 5:
                return tmp.getUrl();
        }
        return null;
    }
    //Setvalue sirve para mandar el objeto, renglon columna, para cambiar ese valor en esa posición
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//Debemos recuperar e objeto, pero debemos definirlo con lo que pusimos en column
        switch (columnIndex){
            case 0:
                //De los datos, vamos a obtener el renglon columna con rowindex y columnindex, para obtener el objeto en el espacio a modificar, pondremos.set y adentro el valor que me están mandando (aValue), acompañado de un casting porque
                // el dato llega siendo de tipo objeto, y en realidad tiene que ser string, int, o el caso que sea.
                datos.get(rowIndex).setID((Integer) aValue);
            case 1:
                datos.get(rowIndex).setNombre((String) aValue);
                break;
            case 2:
                datos.get(rowIndex).setTipo((String) aValue);
                break;
            case 3:
                datos.get(rowIndex).setTamano((String) aValue);
                break;
            case 4:
                datos.get(rowIndex).setAlimentacion((String) aValue);
                break;
            case 5:
                datos.get(rowIndex).setUrl((String) aValue);
                break;
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    //agregarinsecto recibe un insecto y lo agrega al arreglo interno del modelo
    public void agregarInsecto (Insectos insecto){
        datos.add(insecto);

    }
    //haremos un objeto que recibe un indice, y que lo regresa
    public Insectos getInsectoAtIndex (int idx){
        return datos.get(idx);
    }
    public void cargardatos() {
        try {
            ArrayList<Insectos> tirar = ldao.obtenerTodo();
            System.out.println(tirar);
            datos = ldao.obtenerTodo();
        } catch (SQLException sqle) {
            System.out.println("error" + sqle.getMessage());
        }

    }
    public Insectos getInstroindex(int idx){
        return datos.get(idx);
    }
    public boolean agregainso(Insectos inso) {
        boolean resultado = false;
        try {
            if (ldao.insertar(inso)) {
                datos.add(inso);
                resultado = true;
            } else {
                resultado = false;
            }
        } catch (SQLException sqle) {
            System.out.println("Error" + sqle.getMessage());
        }
        return resultado;
    }
    public boolean actualizar(Insectos inso) {
        boolean resultado = false;
        try {
            if (ldao.update(inso)) {
                datos.add(inso);
                resultado = true;
            } else {
                resultado = false;
            }
        } catch (SQLException sqle) {
            System.out.println("Error" + sqle.getMessage());
        }
        return resultado;
    }
    public boolean eliminar(Insectos inso) {
        boolean resultado = false;
        try {
            if (ldao.delete(String.valueOf(inso))) {
                datos.add(inso);
                resultado = true;
            } else {
                resultado = false;
            }
        } catch (SQLException sqle) {
            System.out.println("Error" + sqle.getMessage());
        }
        return resultado;
    }
}
