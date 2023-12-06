/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias.administracion.constancia;

import Modelo.ConexionBaseDatos;
import Modelo.DAO.ConstanciaDAO;
import Modelo.POJO.Constancia;
import Modelo.POJO.Docente;
import Modelo.POJO.Periodo;
import Utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author johno
 */
public class ConsultarConstanciasSolicitadasFXMLControlador implements Initializable {

    @FXML
    private TableView<Constancia> tvConstancias;
    @FXML
    private Label lbDocente;
    @FXML
    private TableColumn<Constancia, String> tcFechaSolicitud;
    @FXML
    private TableColumn<Constancia, String> tcTipo;
    @FXML
    private Button btnConsultar;
    
    private int idConsulta;

    public ObservableList<Constancia> listaConstancias;
    
    public ObservableList<Periodo> listaPeriodos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
    }    

    @FXML
    private void cerrarVentana(ActionEvent event) {
    }

    @FXML
    private void consultarConstancia(ActionEvent event) throws IOException {
        if(!tvConstancias.getSelectionModel().isEmpty()){
            Constancia constanciaConsulta = verificarConstanciaSeleccionado();
            Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
            if(conexionBD != null){
                if(constanciaConsulta != null){
                    if(Utilidades.mostrarDialogoConfirmacion("Consultar constancia","¿Deseas consultar la información de esta constancia?")){
                        FXMLLoader loaderVentanaConsultarConstancias = new FXMLLoader(getClass().getResource("administracion/constancia/ConsultarConstanciaFXML.fxml"));
                        Parent ventanaConsultarConstancias = loaderVentanaConsultarConstancias.load();
                       

                        Scene escenarioConstancias = new Scene(ventanaConsultarConstancias);
                        Stage stageConstancias = new Stage();
                        stageConstancias.setScene(escenarioConstancias);
                        stageConstancias.initModality(Modality.APPLICATION_MODAL);

                        ConsultarConstanciaFXMLControlador controlador = (ConsultarConstanciaFXMLControlador) loaderVentanaConsultarConstancias.getController();
                        //controlador.inicializarConstancia(constanciaConsulta);

                        stageConstancias.showAndWait();
                    }
                }
            }else{
                Utilidades.mostrarAlertaSimple("Error de conexion",
                        "No hay conexión con la base de datos.",
                        Alert.AlertType.ERROR);
            }
        }else{
            Utilidades.mostrarAlertaSimple("Seleccion obligatoria", "Necesita seleccionar una constancia a consultar", Alert.AlertType.WARNING);
        }
    }
    private Constancia verificarConstanciaSeleccionado(){
        int filaSeleccionada = tvConstancias.getSelectionModel().getFocusedIndex();
        return filaSeleccionada >= 0 ? listaConstancias.get(filaSeleccionada) : null;
    }
    
    public void inicializarConstancias(int idDocente) {
        idConsulta = idDocente;
        //lbDocente.setText(docenteSeleccion.getNombreCompleto());
        cargarDatosTabla();
    }
    
    private void configurarTabla(){
        tcFechaSolicitud.setCellValueFactory(new PropertyValueFactory ("fechaSolicitud"));
        tcTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
    }
    
    private void cargarDatosTabla(){
        try{
            listaConstancias = FXCollections.observableArrayList();
            ArrayList<Constancia> constanciaBD = ConstanciaDAO.recuperarConstanciasPorIdDocente(idConsulta);
            if(!constanciaBD.isEmpty()){
                listaConstancias.addAll(constanciaBD);
                tvConstancias.setItems(listaConstancias);
            }else{
                Utilidades.mostrarAlertaSimple("No hay constancias", "No existen constancias relacionadas al docente.", Alert.AlertType.ERROR);
            }
        }catch(SQLException | NullPointerException e){
            Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
