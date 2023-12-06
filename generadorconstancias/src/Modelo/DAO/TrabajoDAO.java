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
    public static ArrayList<Trabajo> recuperarTodoDocenteSinTrabajo(int idDocente) throws SQLException{
        ArrayList<Trabajo> trabajosRegistrados = null;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT t.idTrabajo, t.nombre " +
                                  "FROM trabajo t WHERE t.idTrabajo "
                                                + "NOT IN (SELECT idTrabajo "
                                                + "FROM trabajodocente  "
                                                + "WHERE idDocente = ?);";
                PreparedStatement consultaTrabajo = conexionBD.prepareStatement(consulta);
                consultaTrabajo.setInt(1, idDocente);
                ResultSet resultadoConsulta = consultaTrabajo.executeQuery();
                trabajosRegistrados = new ArrayList<>();
                
                while(resultadoConsulta.next()){
                    Trabajo trabajoTemporal = new Trabajo();
                    trabajoTemporal.setIdTrabajo(resultadoConsulta.getInt("idTrabajo"));
                    trabajoTemporal.setNombre(resultadoConsulta.getString("nombre"));
                    trabajosRegistrados.add(trabajoTemporal);
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar los trabajos: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }           
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return trabajosRegistrados;
    }

                
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
                    trabajosDocente.add(trabajoTemporal.getNombre());
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

    public static void asignarTrabajoADocente(int idDocente, int idTrabajo) throws SQLException {
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consultaAsignacionTrabajoDocente = "INSERT INTO trabajodocente(idDocente,idTrabajo)  VALUES (?,?); ";
                PreparedStatement consultaTrabajoDocente = conexionBD.prepareStatement(consultaAsignacionTrabajoDocente);
                consultaTrabajoDocente.setInt(1, idDocente);
                consultaTrabajoDocente.setInt(2, idTrabajo);
                int filasAfectadas = consultaTrabajoDocente.executeUpdate();
                 
                if(filasAfectadas > 0){
                    Utilidades.mostrarAlertaSimple("Operación finalizada con éxito", "Se asignó el trabajo al docente correctamente.", Alert.AlertType.INFORMATION);
                }else{
                    Utilidades.mostrarAlertaSimple("Operación fallida", "No se pudo asignar el trabajo al docente.", Alert.AlertType.ERROR);
                }
            }catch(SQLException sqlException){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar los software o hardware registrados: " + sqlException.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexión con la base de datos.", Alert.AlertType.ERROR);
        }
    }
}
