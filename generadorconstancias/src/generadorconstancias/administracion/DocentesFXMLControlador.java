/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias.administracion;

import Modelo.POJO.PersonalAdministrativo;
import Utilidades.Utilidades;
import generadorconstancias.InicioSesionFXMLControlador;
import generadorconstancias.administracion.constancia.ConsultarConstanciasSolicitadasFXMLControlador;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author johno
 */
public class DocentesFXMLControlador implements Initializable {
    private PersonalAdministrativo personalSesion;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void registrarDocente(ActionEvent event) {
    }

    @FXML
    private void modificarDocente(ActionEvent event) {
    }

    @FXML
    private void consultarDocente(ActionEvent event) {
    }

    @FXML
    private void eliminarDocente(ActionEvent event) {
    }

    @FXML
    private void registrarTrabajo(ActionEvent event) {
    }

    @FXML
    private void consultarSolicitudes(ActionEvent event) {
        if(!tvDocentes.getSelectionModel().isEmpty()){
            try {
                    int seleccionado = tvDocentes.getSelectionModel().getSelectedItem().getIdDocente();
                    System.out.println(seleccionado);
                    FXMLLoader loaderVentanaConsultarConstancias = new FXMLLoader(getClass().getResource("administracion/ConsultarConstanciasSolicitadasFXML.fxml"));
                    Parent ventanaConsultarConstancias = loaderVentanaConsultarConstancias.load();

                    Scene escenarioConstancias = new Scene(ventanaConsultarConstancias);
                    Stage stageConstancias = new Stage();
                    stageConstancias.setScene(escenarioConstancias);
                    stageConstancias.initModality(Modality.APPLICATION_MODAL);

                    ConsultarConstanciasSolicitadasFXMLControlador controlador = (ConsultarConstanciasSolicitadasFXMLControlador) loaderVentanaConsultarConstancias.getController();
                    controlador.inicializarConstancias(seleccionado);

                    stageConstancias.showAndWait();
                } catch (IOException ex) {
                    Logger.getLogger(InicioSesionFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
        }else{
            Utilidades.mostrarAlertaSimple("Selecciona un docente", "Se debe seleccionar un docente", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void regresar(ActionEvent event) {
    }

    @FXML
    private void cerrarSesión(ActionEvent event) {
    }
 
    public void inicializarVentana(PersonalAdministrativo personalSesion){
        this.personalSesion = personalSesion;
    }
}
