/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author froyl
 */
public class PeriodoDAO {
    public static String recuperarPeriodoPorIdPeriodo(int idPeriodo) throws SQLException{
        String periodo = "";
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT p.periodo FROM periodo p WHERE idPeriodo = ?";
                PreparedStatement consultaPeriodo = conexionBD.prepareStatement(consulta);
                consultaPeriodo.setInt(1, idPeriodo);
                ResultSet resultadoConsulta = consultaPeriodo.executeQuery();
                
                if(resultadoConsulta.next()){
                    periodo = resultadoConsulta.getString("periodo");
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar el periodo: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return periodo;
    }
}
