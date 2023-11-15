/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author froyl
 */
public class ConexionBaseDatos {
    private static String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static String BASEDATOS = "sistemaconstancias";
    private static String IP = "localhost";
    private static String PUERTO = "3306";
    private static String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + BASEDATOS + "?allowPublicKeyRetrieval=true&useSSL=false";
    
    private static String USUARIO = "usuarioconstancias";
    private static String CONTRASENIA = "constanciasUV";
    
    public static Connection abrirConexionBaseDatos(){
        Connection dbConnection = null;
        try {
            Class.forName(CONTROLADOR);
            dbConnection = DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        
        return dbConnection;
    }
}
