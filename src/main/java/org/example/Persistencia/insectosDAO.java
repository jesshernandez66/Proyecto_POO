package org.example.Persistencia;

import org.example.Modelo.Insectos;
import org.example.Persistencia.ConexionSingleton;
import org.example.Persistencia.InterfazDao;

import java.sql.*;
import java.util.ArrayList;

public class insectosDAO implements InterfazDao {
    public insectosDAO() {
    }

    @Override
    public boolean insertar(Object obj) throws SQLException {
        String sqlInsert="INSERT INTO Insectos (Nombre,Tipo,Medida,Alimentacion,Url) VALUES(?,?,?,?,?)";
        int rowCount=0;
        PreparedStatement pstm=ConexionSingleton.getInstance("insectos.db").getConnection().prepareStatement(sqlInsert);
        pstm.setString(1,((Insectos)obj).getNombre());
        pstm.setString(2,((Insectos)obj).getTipo());
        pstm.setString(3,((Insectos)obj).getTamano());
        pstm.setString(4,((Insectos)obj).getAlimentacion());
        pstm.setString(5,((Insectos)obj).getUrl());
        rowCount= pstm.executeUpdate();
        return rowCount>0;
    }

    @Override
    public boolean update(Object obj) throws SQLException {
        String sqlUpdate="UPDATE Insectos SET Nombre=?,Tipo=?,Medida=?,Alimentacion=?, Url=? WHERE ID=?;";
        int rowCount=0;
        PreparedStatement pstm=ConexionSingleton.getInstance("insectos.db").getConnection().prepareStatement(sqlUpdate);
        pstm.setString(1,((Insectos)obj).getNombre());
        pstm.setString(2,((Insectos)obj).getTipo());
        pstm.setString(3,((Insectos)obj).getTamano());
        pstm.setString(4,((Insectos)obj).getAlimentacion());
        pstm.setString(5,((Insectos)obj).getUrl());
        pstm.setInt(6,((Insectos)obj).getID());
        rowCount= pstm.executeUpdate();
        return rowCount>0;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sqlDelete="DELETE FROM Insectos WHERE id=?;";
        int rowCout=0;
        PreparedStatement pstm= ConexionSingleton.getInstance("insectos.db").getConnection().prepareStatement(sqlDelete);
        pstm.setInt(1,Integer.parseInt(id));
        rowCout =pstm.executeUpdate();
        return rowCout>0;
    }

    @Override
    public ArrayList obtenerTodo() throws SQLException {
        String sql = "SELECT * FROM Insectos";
        //inicializamos un arraylist llamado resultado
        ArrayList<Insectos> resultado = new ArrayList<>();
        //Referenciamos la base de datos
        String dbPath = "C:/Users/Lenovo/IdeaProjects/Proyecto_POO_Enyel/src/main/resources/insectos.db";
       //Las conexiones se hacen nulas
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        //establecemos la conexion coon la base de datos, cómo una conexion singleton, otra vez
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
//Con el while sacamos los resultados que se pidan en el objeto insecto
            while (resultSet.next()) {
                // y lo estamos agregando al arraylist resultado
                resultado.add(new Insectos(resultSet.getInt(1), resultSet.getString(2),
                                           //Cambiamos el double a string xq no lo aceptaba
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public Object buscarPorId(String id) throws SQLException {
        //Pedimos el id 
        String sql ="SELECT * FROM Insectos WHERE Id=?";
        Insectos insectos=null;
//mandamos a la base de datos
        PreparedStatement pstm = ConexionSingleton.getInstance("insectos.db").getConnection().prepareStatement(sql);
        pstm.setInt(1,Integer.parseInt(id));
        ResultSet rst= pstm.executeQuery();
        if (rst.next()) {
            //Y pedimos los datos y regresamos el insecto 
            insectos = new Insectos(rst.getInt(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
            return insectos;
        }
        return null;
    }
}
