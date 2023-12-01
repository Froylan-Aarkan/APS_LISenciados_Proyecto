/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.Docente;
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
public class DocenteDAO {
    public static Docente iniciarSesion(String usuario, String contrasenia) throws SQLException{
        Docente usuarioSesion = null;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try {
                String consulta = "SELECT * FROM docente WHERE usuario = ? AND contrasenia = ?";
                
                PreparedStatement consultaLogin = conexionBD.prepareStatement(consulta);
                consultaLogin.setString(1, usuario);
                consultaLogin.setString(2, contrasenia);
                ResultSet resultadoConsulta = consultaLogin.executeQuery();                
                
                if(resultadoConsulta.next()){
                    usuarioSesion = new Docente();
                    usuarioSesion.setIdDocente(resultadoConsulta.getInt("idDocente"));
                    usuarioSesion.setNoPersonal(resultadoConsulta.getInt("noPersonal"));
                    usuarioSesion.setUsuario(resultadoConsulta.getString("usuario"));
                    usuarioSesion.setContrasenia(resultadoConsulta.getString("contrasenia"));
                    usuarioSesion.setNombreCompleto(resultadoConsulta.getString("nombreCompleto"));
                    usuarioSesion.setCorreoInstitucional(resultadoConsulta.getString("correoInstitucional"));
                    usuarioSesion.setNumeroTelefonico(resultadoConsulta.getString("numeroTelefonico"));
                }
            } catch (SQLException e) {
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar iniciar sesión: " + e.getMessage(), Alert.AlertType.ERROR);
            } finally {
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return usuarioSesion;
    }
    
    public static ArrayList<Docente> recuperarDocentes() throws SQLException{
        ArrayList<Docente> docentesRegistrados = null;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT * FROM docente";
                PreparedStatement consultaDocente = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = consultaDocente.executeQuery();
                docentesRegistrados = new ArrayList<>();
                while(resultadoConsulta.next()){
                    Docente docenteTemporal = new Docente();
                    docenteTemporal.setIdDocente(resultadoConsulta.getInt("idDocente"));
                    docenteTemporal.setNoPersonal(resultadoConsulta.getInt("noPersonal"));
                    docenteTemporal.setNombreCompleto(resultadoConsulta.getString("nombreCompleto"));
                    docenteTemporal.setCorreoInstitucional(resultadoConsulta.getString("correoInstitucional"));
                    docenteTemporal.setNumeroTelefonico(resultadoConsulta.getString("numeroTelefonico"));
                    docenteTemporal.setUsuario(resultadoConsulta.getString("usuario"));
                    docenteTemporal.setContrasenia(resultadoConsulta.getString("contrasenia"));
                    docentesRegistrados.add(docenteTemporal);
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar los docentes: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }           
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return docentesRegistrados;
    }
    
    public static boolean registrarDocente(Docente docenteNuevo) throws SQLException{
        boolean resultadoOperacion = false;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String sentencia = "INSERT INTO docente (noPersonal, usuario, contrasenia, nombreCompleto, correoInstitucional, numeroTelefonico) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement sentenciaDocente = conexionBD.prepareStatement(sentencia);
                sentenciaDocente.setInt(1, docenteNuevo.getNoPersonal());
                sentenciaDocente.setString(2, docenteNuevo.getUsuario());
                sentenciaDocente.setString(3, docenteNuevo.getContrasenia());
                sentenciaDocente.setString(4, docenteNuevo.getNombreCompleto());
                sentenciaDocente.setString(5, docenteNuevo.getCorreoInstitucional());
                sentenciaDocente.setString(6, docenteNuevo.getNumeroTelefonico());
                if(sentenciaDocente.executeUpdate() > 0){
                    resultadoOperacion = true;
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar registrar al nuevo docente: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return resultadoOperacion;
    }
    
    public static boolean modificarDocente(Docente docenteModificado) throws SQLException{
        boolean resultadoOperacion = false;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String sentencia = "UPDATE docente SET noPersonal = ?, usuario = ?, contrasenia = ?, nombreCompleto = ?, correoInstitucional = ?, numeroTelefonico = ? WHERE idDocente = ?";
                PreparedStatement sentenciaDocente = conexionBD.prepareStatement(sentencia);
                sentenciaDocente.setInt(1, docenteModificado.getNoPersonal());
                sentenciaDocente.setString(2, docenteModificado.getUsuario());
                sentenciaDocente.setString(3, docenteModificado.getContrasenia());
                sentenciaDocente.setString(4, docenteModificado.getNombreCompleto());
                sentenciaDocente.setString(5, docenteModificado.getCorreoInstitucional());
                sentenciaDocente.setString(6, docenteModificado.getNumeroTelefonico());
                sentenciaDocente.setInt(7, docenteModificado.getIdDocente());            
                if(sentenciaDocente.executeUpdate() > 0){
                    resultadoOperacion = true;
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar modificar al docente: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        return resultadoOperacion;
    }
    
    public static boolean eliminarDocente(int idDocente) throws SQLException{
        boolean resultadoOperacion = false;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String sentencia = "DELETE FROM docente WHERE idDocente = ?";
                PreparedStatement sentenciaDocente = conexionBD.prepareStatement(sentencia);
                sentenciaDocente.setInt(1, idDocente);
                if(sentenciaDocente.executeUpdate() > 0){
                    resultadoOperacion = true;
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar eliminar al docente: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return resultadoOperacion;
    }
    
    public static int buscarIdDocentePorNoPersonal(int noPersonal) throws SQLException{
        int idDocente = 0;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idDocente FROM docente WHERE noPersonal = ?";
                PreparedStatement consultaDocente = conexionBD.prepareStatement(consulta);
                consultaDocente.setInt(1, noPersonal);
                ResultSet resultadoConsulta = consultaDocente.executeQuery();                
                if(resultadoConsulta.next()){
                    idDocente = resultadoConsulta.getInt("idDocente");
                }                
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar buscar al docente: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        return idDocente;
    }
    
    public static int buscarIdDocentePorCorreoInstitucional(String correoInstitucional) throws SQLException{
        int idDocente = 0;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idDocente FROM docente WHERE correoInstitucional = ?";
                PreparedStatement consultaDocente = conexionBD.prepareStatement(consulta);
                consultaDocente.setString(1, correoInstitucional);
                ResultSet resultadoConsulta = consultaDocente.executeQuery();                
                if(resultadoConsulta.next()){
                    idDocente = resultadoConsulta.getInt("idDocente");
                }                
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar buscar al docente: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        return idDocente;
    }
}
