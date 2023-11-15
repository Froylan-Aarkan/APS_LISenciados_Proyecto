/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias;

import Modelo.POJO.Docente;
import Modelo.POJO.PersonalAdministrativo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cerrarSesion(ActionEvent event) {
    }

    @FXML
    private void abrirVentanaDocentes(ActionEvent event) {
    }

    @FXML
    private void solicitarConstancia(ActionEvent event) {
    }

    @FXML
    private void historialSolicitudes(ActionEvent event) {
    }
    
    
    public void iniciarVentanaDocente(Docente docenteSesion){
        btnSolicitarConstancia.setVisible(true);
        btnHistorialSolicitudes.setVisible(true);
        this.docenteSesion = docenteSesion;
    }
    
    public void iniciarVentanaPersonalAdministrativo(PersonalAdministrativo personalSesion){
        btnDocentes.setVisible(true);
        this.personalSesion = personalSesion;
    }
}
