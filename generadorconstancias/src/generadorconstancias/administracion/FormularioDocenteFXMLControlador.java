/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias.administracion;

import Modelo.DAO.DocenteDAO;
import Modelo.POJO.Docente;
import Utilidades.Utilidades;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private AnchorPane apFormulario;

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
        if(camposValidos()){
            Docente docenteNuevo = crearDocente();            
            try {
                if(DocenteDAO.registrarDocente(docenteNuevo)){
                    Utilidades.mostrarAlertaSimple("Registro éxitoso", "Se registró el nuevo docente en el sistema.", Alert.AlertType.INFORMATION);
                    Stage stage = (Stage) btnCancelar.getScene().getWindow();
                    stage.close();
                }
            } catch (SQLException e) {
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void modificarDocente(ActionEvent event) {
        if(camposValidos()){
            Docente docenteModificado = crearDocente();
            docenteModificado.setIdDocente(docente.getIdDocente());
            if(!docente.equals(docenteModificado)){
                try {
                    if(DocenteDAO.modificarDocente(docenteModificado)){
                        Utilidades.mostrarAlertaSimple("Modificación éxitosa", "Se modificó la información del docente con éxito.", Alert.AlertType.INFORMATION);
                        Stage stage = (Stage) btnCancelar.getScene().getWindow();
                        stage.close();
                    }
                } catch (SQLException e) {
                    Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
                }
            }else{
                Utilidades.mostrarAlertaSimple("No hay cambios", "No se detectaron cambios a la información del docente.", Alert.AlertType.WARNING);
            }
        }
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
    
    private boolean camposValidos(){
        boolean camposValidos = true;
        try {  
            if(tfNumeroPersonal.getText().isEmpty()){
                lbErrorNumeroPersonal.setText("No se puede dejar vacío.");
                tfNumeroPersonal.setStyle("-fx-border-color: red");
                camposValidos = false;
            }else if(!esNumerico(tfNumeroPersonal.getText())){
                lbErrorNumeroPersonal.setText("Debe ingresar valores númericos.");
                tfNumeroPersonal.setStyle("-fx-border-color: red");
                camposValidos = false;
            }else if(tfNumeroPersonal.getText().length() > 5){
                lbErrorNumeroPersonal.setText("El número de personal no debe superar los 5 digitos.");
                tfNumeroPersonal.setStyle("-fx-border-color: red");
                camposValidos = false;
            }else if(DocenteDAO.buscarIdDocentePorNoPersonal(Integer.parseInt(tfNumeroPersonal.getText())) != 0 && DocenteDAO.buscarIdDocentePorNoPersonal(Integer.parseInt(tfNumeroPersonal.getText())) != docente.getIdDocente()){
                lbErrorNumeroPersonal.setText("Este número de personal ya existe en el sistema");
                tfNumeroPersonal.setStyle("-fx-border-color: red");
                camposValidos = false;
            }else{
                lbErrorNumeroPersonal.setText("");
                tfNumeroPersonal.setStyle("");
            }
            
            if(tfNombreCompleto.getText().isEmpty()){
                lbErrorNombreCompleto.setText("No se puede dejar vacío.");
                tfNombreCompleto.setStyle("-fx-border-color: red");
                camposValidos = false;
            }else{
                lbErrorNombreCompleto.setText("");
                tfNombreCompleto.setStyle("");
            }
            
            if(tfCorreoInstitucional.getText().isEmpty()){
                lbErrorCorreoInstitucional.setText("No se puede dejar vacío.");
                tfCorreoInstitucional.setStyle("-fx-border-color: red");
                camposValidos = false;
            }else if(!tfCorreoInstitucional.getText().endsWith("@uv.mx")){
                lbErrorCorreoInstitucional.setText("El correo debe pertenecer a la universidad veracruzana.");
                tfCorreoInstitucional.setStyle("-fx-border-color: red");
                camposValidos = false;
            }else if(DocenteDAO.buscarIdDocentePorCorreoInstitucional(tfCorreoInstitucional.getText()) != 0 && DocenteDAO.buscarIdDocentePorCorreoInstitucional(tfCorreoInstitucional.getText()) != docente.getIdDocente()){
                lbErrorCorreoInstitucional.setText("Este correo institucional ya existe en el sistema");
                tfCorreoInstitucional.setStyle("-fx-border-color: red");
                camposValidos = false;
            }else{
                lbErrorCorreoInstitucional.setText("");
                tfCorreoInstitucional.setStyle("");
            }
            
            if(tfNumeroTelefonico.getText().isEmpty()){
                lbErrorNumeroTelefonico.setText("No se puede dejar vacío.");
                tfNumeroTelefonico.setStyle("-fx-border-color: red");
                camposValidos = false;
            }else if(!esNumerico(tfNumeroTelefonico.getText())){
                lbErrorNumeroTelefonico.setText("El número de teléfono debe ser solo numérico.");
                tfNumeroTelefonico.setStyle("-fx-border-color: red");
                camposValidos = false;
            }else if(esNumerico(tfNumeroTelefonico.getText()) && tfNumeroTelefonico.getText().length() != 10){
                lbErrorNumeroTelefonico.setText("El número de teléfono debe contar con 10 digitos.");
                tfNumeroTelefonico.setStyle("-fx-border-color: red");
                camposValidos = false;
            }else{
                lbErrorNumeroTelefonico.setText("");
                tfNumeroTelefonico.setStyle("");
            }
            
            if(tfUsuario.getText().isEmpty()){
                lbErrorUsuario.setText("No se puede dejar vacío.");
                tfUsuario.setStyle("-fx-border-color: red");
                camposValidos = false;
            }else{
                lbErrorUsuario.setText("");
                tfUsuario.setStyle("");
            }
            
            if(pfContrasenia.getText().isEmpty()){
                lbErrorContrasenia.setText("No se puede dejar vacío.");
                pfContrasenia.setStyle("-fx-border-color: red");
                camposValidos = false;
            }else{
                lbErrorContrasenia.setText("");
                pfContrasenia.setStyle("");
            }            
        }catch(SQLException e) {
            Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
        }
        
        return camposValidos;
    }
    
    private boolean esNumerico(String cadena){
        try {
            Long.parseLong(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    private Docente crearDocente(){
        Docente docente = new Docente();
        docente.setNoPersonal(Integer.parseInt(tfNumeroPersonal.getText()));
        docente.setNombreCompleto(tfNombreCompleto.getText());
        docente.setCorreoInstitucional(tfCorreoInstitucional.getText());
        docente.setNumeroTelefonico(tfNumeroTelefonico.getText());
        docente.setUsuario(tfUsuario.getText());
        docente.setContrasenia(pfContrasenia.getText());
        
        return docente;
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
        this.docente = new Docente();
        docente.setIdDocente(0);
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
