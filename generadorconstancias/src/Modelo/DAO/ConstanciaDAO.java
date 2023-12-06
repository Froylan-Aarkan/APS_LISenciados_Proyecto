/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.Constancia;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author froyl
 */
public class ConstanciaDAO {
    public static ArrayList<Constancia> recuperarConstanciasPorIdDocente(int idDoccente) throws SQLException{
        ArrayList<Constancia> constanciasDocente = null;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT * FROM constancia WHERE docenteSolicitud = ?";
                PreparedStatement consultaConstancia = conexionBD.prepareStatement(consulta);
                consultaConstancia.setInt(1, idDoccente);
                ResultSet resultadoConsulta = consultaConstancia.executeQuery();
                constanciasDocente = new ArrayList<>();
                
                while(resultadoConsulta.next()){
                    Constancia constanciaTemporal = new Constancia();
                    constanciaTemporal.setIdConstancia(resultadoConsulta.getInt("idConstancia"));
                    constanciaTemporal.setDocenteSolicitud(resultadoConsulta.getInt("docenteSolicitud"));
                    constanciaTemporal.setIdFirmaDigital(resultadoConsulta.getInt("idFirmaDigital"));
                    constanciaTemporal.setPeriodoSolicitud(resultadoConsulta.getInt("idPeriodo"));
                    constanciaTemporal.setFechaSolicitud(resultadoConsulta.getDate("fechaSolicitud"));
                    constanciaTemporal.setTipo(resultadoConsulta.getString("tipo"));
                    constanciaTemporal.setDescripcion(resultadoConsulta.getString("descripcion"));
                    constanciaTemporal.setPeriodo(PeriodoDAO.recuperarPeriodoPorIdPeriodo(resultadoConsulta.getInt("idPeriodo")));
                    constanciasDocente.add(constanciaTemporal);
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar las constancias: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return constanciasDocente;
    }
    
    public static boolean registrarConstancia(Constancia constanciaNueva) throws SQLException{
        boolean resultadoOperacion = false;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String sentencia = "INSERT INTO constancia (docenteSolicitud, fechaSolicitud, descripcion, tipo, idPeriodo) VALUES (?, ?, 'constancia', ?, 1)";
                PreparedStatement sentenciaDocente = conexionBD.prepareStatement(sentencia);
                sentenciaDocente.setInt(1, constanciaNueva.getDocenteSolicitud());
                sentenciaDocente.setDate(2, constanciaNueva.getFechaSolicitud());
               // sentenciaDocente.setString(3, constanciaNueva.getDescripcion());
                sentenciaDocente.setString(3, constanciaNueva.getTipo());
                //sentenciaDocente.setInt(5, constanciaNueva.getIdFirmaDigital());
                //sentenciaDocente.setInt(6, constanciaNueva.getPeriodoSolicitud());
                if(sentenciaDocente.executeUpdate() > 0){
                    resultadoOperacion = true;
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar registrar la nueva constancia: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return resultadoOperacion;
    }
}
