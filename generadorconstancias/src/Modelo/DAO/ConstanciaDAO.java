/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.Constancia;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import Modelo.POJO.Periodo;
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
                    constanciaTemporal.setFirma(FirmaDigitalDAO.recuperarFirmaPorIdFirma(resultadoConsulta.getInt("idFirmaDigital")));
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
}
