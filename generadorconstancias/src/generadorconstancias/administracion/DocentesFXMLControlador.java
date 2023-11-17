/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias.administracion;

import Modelo.POJO.PersonalAdministrativo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
