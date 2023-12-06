/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.Trabajo;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author froyl
 */
public class TrabajoDAO {
    public static ArrayList<String> recuperarTrabajosPorIdDocente(int idDocente) throws SQLException{
        ArrayList<String> trabajosDocente = null;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT trabajo.idTrabajo, trabajo.nombre FROM trabajo inner join trabajodocente where trabajo.idTrabajo = trabajodocente.idTrabajo and trabajodocente.idDocente = ?";
                PreparedStatement consultaConstancia = conexionBD.prepareStatement(consulta);
                consultaConstancia.setInt(1, idDocente);
                ResultSet resultadoConsulta = consultaConstancia.executeQuery();
                trabajosDocente = new ArrayList<>();
                
                while(resultadoConsulta.next()){
                    Trabajo trabajoTemporal = new Trabajo();
                    trabajoTemporal.setIdTrabajo(resultadoConsulta.getInt("idTrabajo"));
                    trabajoTemporal.setNombre(resultadoConsulta.getString("nombre"));
                    trabajosDocente.add(resultadoConsulta.getString("nombre"));
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar los trabajos: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return trabajosDocente;
    }
}
