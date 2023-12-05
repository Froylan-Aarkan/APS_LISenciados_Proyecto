/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias.administracion.constancia;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author johno
 */
public class ConsultarConstanciasSolicitadasFXMLControlador implements Initializable {

    @FXML
    private TableView<?> tvConstancias;
    @FXML
    private Label lbDocente;
    @FXML
    private TableColumn<?, ?> tcFechaSolicitud;
    @FXML
    private TableColumn<?, ?> tcPeriodoSolicitud;
    @FXML
    private TableColumn<?, ?> tcTipo;
    @FXML
    private Button btnConsultar;
    
    private int idConsulta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cerrarVentana(ActionEvent event) {
    }

    @FXML
    private void consultarConstancia(ActionEvent event) {
    }
    
    public void inicializarConstancias(int idDocente) {
        idConsulta = idDocente;
    }
}
