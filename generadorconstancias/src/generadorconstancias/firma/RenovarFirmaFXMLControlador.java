/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias.firma;

import Modelo.POJO.PersonalAdministrativo;
import Utilidades.Utilidades;
import generadorconstancias.MenuPrincipalFXMLControlador;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author froyl
 */
public class RenovarFirmaFXMLControlador implements Initializable {
    private PersonalAdministrativo personalSesion;
    
    @FXML
    private ImageView ivFirma;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresar(ActionEvent event) {
        try {
            FXMLLoader loaderMenuPrincipal = new FXMLLoader(getClass().getResource("/generadorconstancias/MenuPrincipalFXML.fxml"));
            Parent menuPrincipal = loaderMenuPrincipal.load();
            MenuPrincipalFXMLControlador controlador = loaderMenuPrincipal.getController();
            Scene escenaMenuPrincipal = new Scene(menuPrincipal);
            Stage stageMenuPrincipal = (Stage) ivFirma.getScene().getWindow();
            stageMenuPrincipal.setScene(escenaMenuPrincipal);
            stageMenuPrincipal.setResizable(false);
            stageMenuPrincipal.setTitle("Menú Principal");
            controlador.iniciarVentanaPersonalAdministrativo(personalSesion);
            stageMenuPrincipal.show();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salio mal: " + e.getMessage() + ".", Alert.AlertType.ERROR);
        }        
    }

    @FXML
    private void subirImagen(ActionEvent event) {
    }

    @FXML
    private void guardarFirma(ActionEvent event) {
    }

    @FXML
    private void cerrarSesión(ActionEvent event) {
        if(Utilidades.mostrarDialogoConfirmacion("Cerrar sesión", "¿Seguro que desea cerrar sesión?")){
            try {
                FXMLLoader loaderInicioSesion = new FXMLLoader(getClass().getResource("/generadorconstancias/InicioSesionFXML.fxml"));
                Parent inicioSesion = loaderInicioSesion.load();

                Scene escenaIniciarSesion = new Scene(inicioSesion);
                Stage stageInicioSesion = (Stage) ivFirma.getScene().getWindow();
                stageInicioSesion.setScene(escenaIniciarSesion);
                stageInicioSesion.setResizable(false);
                stageInicioSesion.setTitle("Iniciar Sesión");
                stageInicioSesion.show();
            } catch (IOException e) {
                Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salio mal: " + e.getMessage() + ".", Alert.AlertType.ERROR);
            }
        }
    }
    
    public void inicializarVentana(PersonalAdministrativo personalSesion){
        this.personalSesion = personalSesion;
    }
}
