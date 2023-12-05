/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Utilidades.Utilidades;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 *
 * @author froyl
 */
public class FirmaDigitalDAO {
    public static byte[] recuperarUltimaFirma() throws SQLException{
        byte[] firma = null;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT firma FROM firmadigital";
                PreparedStatement consultaFirma = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = consultaFirma.executeQuery();
                while(resultadoConsulta.next()){
                    firma = resultadoConsulta.getBytes("firma");
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar las firmas registradas: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }          
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return firma;
    }
    
    public static boolean guardarFirma(File firma) throws SQLException{
        boolean resultadoOperacion = false;    
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String sentencia1 = "DELETE FROM firmadigital WHERE idFirmaDigital = 1"; 
                String sentencia2 = "INSERT INTO firmadigital (idFirmaDigital, firma) VALUES (?, ?)";
                Statement sentenciaDelete = conexionBD.createStatement();
                sentenciaDelete.executeUpdate(sentencia1);
                PreparedStatement sentenciaFirma = conexionBD.prepareStatement(sentencia2);
                sentenciaFirma.setInt(1, 1);
                FileInputStream archivoFirma = new FileInputStream(firma);
                sentenciaFirma.setBlob(2, archivoFirma);

                if(sentenciaFirma.executeUpdate() > 0){
                    resultadoOperacion = true;
                }              
            }catch(SQLException | FileNotFoundException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar guardar la firma: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return resultadoOperacion;
    }
}
