/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias.administracion;

import Modelo.POJO.Docente;
import Utilidades.Utilidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author froyl
 */
public class FormularioDocenteFXMLControlador implements Initializable {
    private Docente docente;

    @FXML
    private Label lbTitulo;
    @FXML
    private TextField tfNombreCompleto;
    @FXML
    private TextField tfNumeroPersonal;
    @FXML
    private TextField tfCorreoInstitucional;
    @FXML
    private TextField tfNumeroTelefonico;
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField pfContrasenia;
    @FXML
    private Label lbErrorNumeroPersonal;
    @FXML
    private Label lbErrorUsuario;
    @FXML
    private Label lbErrorNombreCompleto;
    @FXML
    private Label lbErrorCorreoInstitucional;
    @FXML
    private Label lbErrorNumeroTelefonico;
    @FXML
    private Label lbErrorContrasenia;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnModificar;

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
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
        }   
    }

    @FXML
    private void registrarDocente(ActionEvent event) {
        //TODO validar campos, validar noPersonal repetido
    }

    @FXML
    private void modificarDocente(ActionEvent event) {
        //TODO validar campos, validar noPersonal repetido, validar cambios
    }
    
     @FXML
    private void cancelar(ActionEvent event) {
        if(lbTitulo.getText().startsWith("Registrar")){
            if(Utilidades.mostrarDialogoConfirmacion("Cancelar", "¿Seguro que desea borrar los campos?")){
                tfNombreCompleto.setText("");
                tfCorreoInstitucional.setText("");
                tfNumeroPersonal.setText("");
                tfNumeroTelefonico.setText("");
                tfUsuario.setText("");
                pfContrasenia.setText("");
            }
        }else if(lbTitulo.getText().startsWith("Modificar")){
            if(Utilidades.mostrarDialogoConfirmacion("Cancelar", "¿Seguro que desea revertir los campos?")){
                tfNombreCompleto.setText(docente.getNombreCompleto());
                tfCorreoInstitucional.setText(docente.getCorreoInstitucional());
                tfNumeroPersonal.setText(String.valueOf(docente.getNoPersonal()));
                tfNumeroTelefonico.setText(docente.getNumeroTelefonico());
                tfUsuario.setText(docente.getUsuario());
                pfContrasenia.setText(docente.getContrasenia());
            }
        }
    }
    
    private void llenarCampos(){
        tfNombreCompleto.setText(docente.getNombreCompleto());
        tfCorreoInstitucional.setText(docente.getCorreoInstitucional());
        tfNumeroPersonal.setText(String.valueOf(docente.getNoPersonal()));
        tfNumeroTelefonico.setText(docente.getNumeroTelefonico());
        tfUsuario.setText(docente.getUsuario());
        pfContrasenia.setText(docente.getContrasenia());
    }
    
    public void inicializarVentanaRegistro(){
        lbTitulo.setText("Registrar Docente");
        btnRegistrar.setVisible(true);
        btnCancelar.setVisible(true);
    }
    
    public void inicializarVentanaModificacion(Docente docenteModificacion){
        this.docente = docenteModificacion;
        lbTitulo.setText("Modificar Docente");
        btnModificar.setVisible(true);
        btnCancelar.setVisible(true);
        llenarCampos();
    }
    
    public void inicializarVentanaConsulta(Docente docenteConsulta){
        this.docente = docenteConsulta;
        lbTitulo.setText("Consultar Docente");
        llenarCampos();
        tfNombreCompleto.setEditable(false);
        tfCorreoInstitucional.setEditable(false);
        tfNumeroPersonal.setEditable(false);
        tfNumeroTelefonico.setEditable(false);
        tfUsuario.setEditable(false);
        pfContrasenia.setEditable(false);
    }  
}
