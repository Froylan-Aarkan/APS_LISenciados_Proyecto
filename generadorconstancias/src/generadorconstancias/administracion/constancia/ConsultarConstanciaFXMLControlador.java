/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias.administracion.constancia;

import Modelo.DAO.FirmaDigitalDAO;
import Modelo.DAO.PeriodoDAO;
import Modelo.POJO.Constancia;
import Modelo.POJO.FirmaDigital;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Utilidades.Utilidades;
import java.sql.Date;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author johno
 */
public class ConsultarConstanciaFXMLControlador implements Initializable {

    @FXML
    private ImageView ivFirma;
    @FXML
    private Label lbTipo;
    @FXML
    private Label lbPeriodoSolicitud;
    @FXML
    private Label lbFechaSolicitud;
    @FXML
    private Label lbDocente;
    
    private Constancia consultaConstancia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresar(ActionEvent event) {
        if(Utilidades.mostrarDialogoConfirmacion("Regresar", "¿Seguro que desea cancelar la operación y volver a la ventana anterior?")){
            Stage stage = (Stage) lbDocente.getScene().getWindow();
            stage.close();
        }
    }
    
    
    void inicializarConstancia(Constancia constanciaSeleccion, String nombreDocente) throws SQLException {
        try{
            if(constanciaSeleccion != null){
                
                consultaConstancia = constanciaSeleccion;
                lbDocente.setText(nombreDocente);
                lbFechaSolicitud.setText(String.valueOf(constanciaSeleccion.getFechaSolicitud()));
                lbTipo.setText(constanciaSeleccion.getTipo());
                lbPeriodoSolicitud.setText(PeriodoDAO.recuperarPeriodoPorIdPeriodo(constanciaSeleccion.getPeriodoSolicitud()));
                
                if (constanciaSeleccion.getIdFirmaDigital() > 0) {
                    Image img = new Image(new ByteArrayInputStream(constanciaSeleccion.getFirma()));
                    ivFirma.setImage(img);
                }else{
                    Utilidades.mostrarAlertaSimple("Firma sin encontrar", "No se ha detectado una firma existente para esta constancia", Alert.AlertType.WARNING);
                }
            }
        }catch(SQLException exception){
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
    }
}
