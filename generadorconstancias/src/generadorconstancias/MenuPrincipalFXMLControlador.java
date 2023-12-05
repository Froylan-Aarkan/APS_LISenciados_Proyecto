/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias;

import Modelo.POJO.Docente;
import Modelo.POJO.PersonalAdministrativo;
import Utilidades.Utilidades;
import generadorconstancias.administracion.DocentesFXMLControlador;
import generadorconstancias.firma.RenovarFirmaFXMLControlador;
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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author froyl
 */
public class MenuPrincipalFXMLControlador implements Initializable {
    private Docente docenteSesion;
    private PersonalAdministrativo personalSesion;
    
    @FXML
    private Button btnDocentes;
    @FXML
    private Button btnSolicitarConstancia;
    @FXML
    private Button btnHistorialSolicitudes;
    @FXML
    private Button btnRenovarFirma;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cerrarSesion(ActionEvent event) {
        if(Utilidades.mostrarDialogoConfirmacion("Cerrar sesión", "¿Seguro que desea cerrar sesión?")){
            try {
                FXMLLoader loaderInicioSesion = new FXMLLoader(getClass().getResource("InicioSesionFXML.fxml"));
                Parent inicioSesion = loaderInicioSesion.load();

                Scene escenaVentanaPrincipal = new Scene(inicioSesion);
                Stage stageInicioSesion = (Stage) btnDocentes.getScene().getWindow();
                stageInicioSesion.setScene(escenaVentanaPrincipal);
                stageInicioSesion.setResizable(false);
                stageInicioSesion.setTitle("Iniciar Sesión");
                stageInicioSesion.show();
            } catch (IOException e) {
                Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salio mal: " + e.getMessage() + ".", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void abrirVentanaDocentes(ActionEvent event) {
        try {
            FXMLLoader loaderDocentes = new FXMLLoader(getClass().getResource("administracion/DocentesFXML.fxml"));
            Parent docentes = loaderDocentes.load();
            DocentesFXMLControlador controlador = loaderDocentes.getController();
            Scene escenaDocentes = new Scene(docentes);
            Stage stageDocentes = (Stage) btnDocentes.getScene().getWindow();
            stageDocentes.setScene(escenaDocentes);
            stageDocentes.setResizable(false);
            stageDocentes.setTitle("Docentes");
            controlador.inicializarVentana(personalSesion);
            stageDocentes.show();
        }catch(IOException e){
            Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salio mal: " + e.getMessage() + ".", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void solicitarConstancia(ActionEvent event) {
    }

    @FXML
    private void historialSolicitudes(ActionEvent event) {
    }
    
    @FXML
    private void abrirRenovarFirma(ActionEvent event) {
        try{
            FXMLLoader loaderFirma = new FXMLLoader(getClass().getResource("firma/RenovarFirmaFXML.fxml"));
            Parent firma = loaderFirma.load();
            RenovarFirmaFXMLControlador controlador = loaderFirma.getController();
            Scene escenaFirma = new Scene(firma);
            Stage stageFirma = (Stage) btnRenovarFirma.getScene().getWindow();
            stageFirma.setScene(escenaFirma);
            stageFirma.setResizable(false);
            stageFirma.setTitle("Renovar Firma");
            controlador.inicializarVentana(personalSesion);
            stageFirma.show();
        }catch(IOException e){
            Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salio mal: " + e.getMessage() + ".", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
    public void iniciarVentanaDocente(Docente docenteSesion){
        btnSolicitarConstancia.setVisible(true);
        btnHistorialSolicitudes.setVisible(true);
        this.docenteSesion = docenteSesion;
    }
    
    public void iniciarVentanaPersonalAdministrativo(PersonalAdministrativo personalSesion){
        btnDocentes.setVisible(true);
        btnRenovarFirma.setVisible(true);
        this.personalSesion = personalSesion;
    }  
}
