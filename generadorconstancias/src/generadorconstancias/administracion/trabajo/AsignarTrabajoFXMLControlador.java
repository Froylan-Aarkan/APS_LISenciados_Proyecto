/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias.administracion.trabajo;

import Modelo.ConexionBaseDatos;
import Modelo.DAO.TrabajoDAO;
import Modelo.POJO.PersonalAdministrativo;
import Modelo.POJO.Trabajo;
import Utilidades.Utilidades;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author johno
 */
public class AsignarTrabajoFXMLControlador implements Initializable {

    @FXML
    private Label lbDocente;
    @FXML
    private TableView<Trabajo> tvTrabajos;
    @FXML
    private TableColumn<Trabajo, String> tcNombre;
    @FXML
    private Button btnAsignar;

    private PersonalAdministrativo personalSesion;
    
    public ObservableList<Trabajo> listaTrabajos;
    
    private String nombreCompletoDocente;
    
    private int idConsulta;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
    }

    private Trabajo verificarTrabajoSeleccionado(){
        int filaSeleccionada = tvTrabajos.getSelectionModel().getFocusedIndex();
        return filaSeleccionada >= 0 ? listaTrabajos.get(filaSeleccionada) : null;
    }
    
    public void inicializarTrabajos(int idDocente, String nombreCompleto) throws SQLException {
        idConsulta = idDocente;
        nombreCompletoDocente = nombreCompleto;
        lbDocente.setText(nombreCompletoDocente);
        cargarDatosTabla();
    }
    
    private void configurarTabla(){
        tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
    }    

    private void cargarDatosTabla() throws SQLException{
        try{
            listaTrabajos = FXCollections.observableArrayList();
            ArrayList<Trabajo> trabajoBD = TrabajoDAO.recuperarTodoDocenteSinTrabajo(idConsulta);
            if(!trabajoBD.isEmpty()){
                listaTrabajos.addAll(trabajoBD);
                tvTrabajos.setItems(listaTrabajos);
            }else{
                Utilidades.mostrarAlertaSimple("No hay trabajos", "No existen más trabajos registrados disponibles para el usuario.", Alert.AlertType.ERROR);
            }
        }catch(NullPointerException e){
            Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void cerrarVentana(ActionEvent event) {
        if(Utilidades.mostrarDialogoConfirmacion("Regresar", "¿Seguro que desea cancelar la operación y volver a la ventana anterior?")){
            Stage stage = (Stage) btnAsignar.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void asignarTrabajo(ActionEvent event) {
        try{
            if(!tvTrabajos.getSelectionModel().isEmpty()){
                    Trabajo trabajoAsignacion = verificarTrabajoSeleccionado();
                    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
                    if(conexionBD != null){
                        if(trabajoAsignacion != null){
                            if(Utilidades.mostrarDialogoConfirmacion("Asignar trabajo","¿Desea asignar el trabajo al docente?")){
                                TrabajoDAO.asignarTrabajoADocente(idConsulta,trabajoAsignacion.getIdTrabajo());
                                cargarDatosTabla();
                            }
                        }
                    }
                }else{
                    Utilidades.mostrarAlertaSimple("Seleccion obligatoria", "Necesita seleccionar un trabajo a relacionar", Alert.AlertType.WARNING);
                }
        }catch(SQLException e){
            Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    public void inicializarVentana(PersonalAdministrativo personalSesion){
        this.personalSesion = personalSesion;
    }
    
}
