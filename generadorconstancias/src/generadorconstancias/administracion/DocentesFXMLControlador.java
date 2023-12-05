/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias.administracion;

import Modelo.DAO.DocenteDAO;
import Modelo.POJO.Docente;
import Modelo.POJO.PersonalAdministrativo;
import Utilidades.Utilidades;
import generadorconstancias.InicioSesionFXMLControlador;
import generadorconstancias.MenuPrincipalFXMLControlador;
import generadorconstancias.administracion.constancia.ConsultarConstanciasSolicitadasFXMLControlador;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author johno
 */
public class DocentesFXMLControlador implements Initializable {
    private ObservableList<Docente> listaDocentes;
    private PersonalAdministrativo personalSesion;
    @FXML
    private TableView<Docente> tvDocentes;
    @FXML
    private TableColumn tcNumeroPersonal;
    @FXML
    private TableColumn tcNombreCompleto;
    @FXML
    private TableColumn tcCorreoInstitucional;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarTabla();
    }    
    
    @FXML
    private void registrarDocente(ActionEvent event) {
        try {
            FXMLLoader loaderFormularioDocente = new FXMLLoader(getClass().getResource("FormularioDocenteFXML.fxml"));
            Parent formularioDocente = loaderFormularioDocente.load();
            FormularioDocenteFXMLControlador controlador = loaderFormularioDocente.getController();
            Scene escenaFormularioDocente = new Scene(formularioDocente);
            Stage stageFormularioDocente = new Stage();
            stageFormularioDocente.setScene(escenaFormularioDocente);
            stageFormularioDocente.initModality(Modality.APPLICATION_MODAL);
            stageFormularioDocente.setResizable(false);
            stageFormularioDocente.setTitle("Registrar Docente");
            controlador.inicializarVentanaRegistro();
            stageFormularioDocente.showAndWait();
            cargarTabla();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void modificarDocente(ActionEvent event) {
        if(verificarSeleccion()){
            try {
                FXMLLoader loaderFormularioDocente = new FXMLLoader(getClass().getResource("FormularioDocenteFXML.fxml"));
                Parent formularioDocente = loaderFormularioDocente.load();
                FormularioDocenteFXMLControlador controlador = loaderFormularioDocente.getController();
                Scene escenaFormularioDocente = new Scene(formularioDocente);
                Stage stageFormularioDocente = new Stage();
                stageFormularioDocente.setScene(escenaFormularioDocente);
                stageFormularioDocente.initModality(Modality.APPLICATION_MODAL);
                stageFormularioDocente.setResizable(false);
                stageFormularioDocente.setTitle("Modificar Docente");
                controlador.inicializarVentanaModificacion(tvDocentes.getSelectionModel().getSelectedItem());
                stageFormularioDocente.showAndWait();
                cargarTabla();
            } catch (IOException e) {
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Docente no seleccionado", "Debe seleccionar a un docente para proceder.", Alert.AlertType.WARNING);
        }       
    }

    @FXML
    private void consultarDocente(ActionEvent event) {
        if(verificarSeleccion()){
            try {
                FXMLLoader loaderFormularioDocente = new FXMLLoader(getClass().getResource("FormularioDocenteFXML.fxml"));
                Parent formularioDocente = loaderFormularioDocente.load();
                FormularioDocenteFXMLControlador controlador = loaderFormularioDocente.getController();
                Scene escenaFormularioDocente = new Scene(formularioDocente);
                Stage stageFormularioDocente = new Stage();
                stageFormularioDocente.setScene(escenaFormularioDocente);
                stageFormularioDocente.initModality(Modality.APPLICATION_MODAL);
                stageFormularioDocente.setResizable(false);
                stageFormularioDocente.setTitle("Consultar Docente");
                controlador.inicializarVentanaConsulta(tvDocentes.getSelectionModel().getSelectedItem());
                stageFormularioDocente.showAndWait();
                cargarTabla();
            } catch (IOException e) {
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Docente no seleccionado", "Debe seleccionar a un docente para proceder.", Alert.AlertType.WARNING);
        }       
    }

    @FXML
    private void eliminarDocente(ActionEvent event) {
        if(verificarSeleccion()){
            if(Utilidades.mostrarDialogoConfirmacion("Eliminar docente", "¿Seguro que desea eliminar al docente seleccionado?")){
                try {
                    if(DocenteDAO.eliminarDocente(tvDocentes.getSelectionModel().getSelectedItem().getIdDocente())){
                        Utilidades.mostrarAlertaSimple("Docente eliminado", "Se eliminó al docente con éxito", Alert.AlertType.INFORMATION);
                    }
                } catch (SQLException e) {
                    Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
                }
            }            
        }else{
            Utilidades.mostrarAlertaSimple("Docente no seleccionado", "Debe seleccionar a un docente para proceder.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void registrarTrabajo(ActionEvent event) {
        if(verificarSeleccion()){
            
        }else{
            Utilidades.mostrarAlertaSimple("Docente no seleccionado", "Debe seleccionar a un docente para proceder.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void consultarSolicitudes(ActionEvent event) {
        if(verificarSeleccion()){
            try {
                    int seleccionado = tvDocentes.getSelectionModel().getSelectedItem().getIdDocente();
                    System.out.println(seleccionado);
                    FXMLLoader loaderVentanaConsultarConstancias = new FXMLLoader(getClass().getResource("constancia/ConsultarConstanciasSolicitadasFXML.fxml"));
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
            Utilidades.mostrarAlertaSimple("Docente no seleccionado", "Debe seleccionar a un docente para proceder.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void regresar(ActionEvent event) {
         try {
            FXMLLoader loaderMenuPrincipal = new FXMLLoader(getClass().getResource("/generadorconstancias/MenuPrincipalFXML.fxml"));
            Parent menuPrincipal = loaderMenuPrincipal.load();
            MenuPrincipalFXMLControlador controlador = loaderMenuPrincipal.getController();
            Scene escenaMenuPrincipal = new Scene(menuPrincipal);
            Stage stageMenuPrincipal = (Stage) tvDocentes.getScene().getWindow();
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
    private void cerrarSesión(ActionEvent event) {
        if(Utilidades.mostrarDialogoConfirmacion("Cerrar sesión", "¿Seguro que desea cerrar sesión?")){
            try {
                FXMLLoader loaderInicioSesion = new FXMLLoader(getClass().getResource("/generadorconstancias/InicioSesionFXML.fxml"));
                Parent inicioSesion = loaderInicioSesion.load();
                Scene escenaIniciarSesion = new Scene(inicioSesion);
                Stage stageInicioSesion = (Stage) tvDocentes.getScene().getWindow();
                stageInicioSesion.setScene(escenaIniciarSesion);
                stageInicioSesion.setResizable(false);
                stageInicioSesion.setTitle("Iniciar Sesión");
                stageInicioSesion.show();
            } catch (IOException e) {
                Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salio mal: " + e.getMessage() + ".", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }
    }
    
    private void configurarTabla(){
        tcNumeroPersonal.setCellValueFactory(new PropertyValueFactory("noPersonal"));
        tcNombreCompleto.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
        tcCorreoInstitucional.setCellValueFactory(new PropertyValueFactory("correoInstitucional"));
    }
    
    private void cargarTabla(){
        try{
            listaDocentes = FXCollections.observableArrayList();
            ArrayList<Docente> docentesBD = DocenteDAO.recuperarDocentes();
            listaDocentes.addAll(docentesBD);
            tvDocentes.setItems(listaDocentes);
        }catch(SQLException e){
            Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
    private boolean verificarSeleccion(){
        return tvDocentes.getSelectionModel().getSelectedItem() != null;
    }
 
    public void inicializarVentana(PersonalAdministrativo personalSesion){
        this.personalSesion = personalSesion;
    }
}
