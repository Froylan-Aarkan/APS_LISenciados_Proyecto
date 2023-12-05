/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias.constancias;

import Modelo.DAO.ConstanciaDAO;
import Modelo.POJO.Constancia;
import Modelo.POJO.Docente;
import Utilidades.Utilidades;
import generadorconstancias.MenuPrincipalFXMLControlador;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author froyl
 */
public class HistorialSolicitudConstanciasFXMLControlador implements Initializable {
    private Docente docenteSesion;
    private ObservableList<Constancia> listaConstancias;

    @FXML
    private TableView<Constancia> tvConstancias;
    @FXML
    private TableColumn tcFecha;
    @FXML
    private TableColumn tcPeriodo;
    @FXML
    private TableColumn tcTipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void cerrarSesión(ActionEvent event) {
        if(Utilidades.mostrarDialogoConfirmacion("Cerrar sesión", "¿Seguro que desea cerrar sesión?")){
            try {
                FXMLLoader loaderInicioSesion = new FXMLLoader(getClass().getResource("/generadorconstancias/InicioSesionFXML.fxml"));
                Parent inicioSesion = loaderInicioSesion.load();
                Scene escenaIniciarSesion = new Scene(inicioSesion);
                Stage stageInicioSesion = (Stage) tvConstancias.getScene().getWindow();
                stageInicioSesion.setScene(escenaIniciarSesion);
                stageInicioSesion.setResizable(false);
                stageInicioSesion.setTitle("Iniciar Sesión");
                stageInicioSesion.show();
            } catch (IOException e) {
                Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salio mal: " + e.getMessage() + ".", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void regresar(ActionEvent event) {
        try {
            FXMLLoader loaderMenuPrincipal = new FXMLLoader(getClass().getResource("/generadorconstancias/MenuPrincipalFXML.fxml"));
            Parent menuPrincipal = loaderMenuPrincipal.load();
            MenuPrincipalFXMLControlador controlador = loaderMenuPrincipal.getController();
            Scene escenaMenuPrincipal = new Scene(menuPrincipal);
            Stage stageMenuPrincipal = (Stage) tvConstancias.getScene().getWindow();
            stageMenuPrincipal.setScene(escenaMenuPrincipal);
            stageMenuPrincipal.setResizable(false);
            stageMenuPrincipal.setTitle("Menú Principal");
            controlador.iniciarVentanaDocente(docenteSesion);
            stageMenuPrincipal.show();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salio mal: " + e.getMessage() + ".", Alert.AlertType.ERROR);
        }       
    }
    
    private void configurarTabla(){
        tcFecha.setCellValueFactory(new PropertyValueFactory("fechaSolicitud"));
        tcPeriodo.setCellValueFactory(new PropertyValueFactory("periodo"));
        tcTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
    }
    
    private void cargarTabla(){
        try{
            listaConstancias = FXCollections.observableArrayList();
            ArrayList<Constancia> constanciasDocente = ConstanciaDAO.recuperarConstanciasPorIdDocente(docenteSesion.getIdDocente());
            listaConstancias.addAll(constanciasDocente);
            tvConstancias.setItems(listaConstancias);
        }catch(SQLException e){
            Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    public void inicializarVentana(Docente docenteSesion){
        this.docenteSesion = docenteSesion;
        configurarTabla();
        cargarTabla();
    }
}
