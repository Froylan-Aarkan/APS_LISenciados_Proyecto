/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias.constancias;

import Modelo.DAO.ConstanciaDAO;
import Modelo.DAO.TrabajoDAO;
import Modelo.POJO.Constancia;
import Modelo.POJO.Docente;
import Utilidades.Utilidades;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import generadorconstancias.MenuPrincipalFXMLControlador;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
/**
 * FXML Controller class
 *
 * @author Elian
 */
public class SolicitarConstanciaFXMLControlador implements Initializable {


    @FXML
    private ComboBox<String> cbConstancia;
    @FXML
    private ComboBox<String> cbTrabajo;
    private Docente docenteSesion;
    private String constanciaTxt;
    private ObservableList<String> listaConstancias;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbTrabajo.setDisable(true);
        
    }    
    
    @FXML
    private void regresar(ActionEvent event) {
        try {
            FXMLLoader loaderMenuPrincipal = new FXMLLoader(getClass().getResource("/generadorconstancias/MenuPrincipalFXML.fxml"));
            Parent menuPrincipal = loaderMenuPrincipal.load();
            MenuPrincipalFXMLControlador controlador = loaderMenuPrincipal.getController();
            Scene escenaMenuPrincipal = new Scene(menuPrincipal);
            Stage stageMenuPrincipal = (Stage) cbConstancia.getScene().getWindow();
            stageMenuPrincipal.setScene(escenaMenuPrincipal);
            stageMenuPrincipal.setResizable(false);
            stageMenuPrincipal.setTitle("Menú Principal");
            controlador.iniciarVentanaDocente(docenteSesion);
            stageMenuPrincipal.show();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salio mal: " + e.getMessage() + ".", Alert.AlertType.ERROR);
        } 
    }

    @FXML
    private void solicitarConstancia(ActionEvent event) {
        String texto = crearConstancia();
        guardarConstancia(texto);
        
        Date fecha = java.sql.Date.valueOf(LocalDate.now());
        try { 
            Constancia constancia = new Constancia();
            constancia.setDocenteSolicitud(docenteSesion.getIdDocente());
            constancia.setFechaSolicitud((java.sql.Date) fecha);
            constancia.setTipo(cbConstancia.getSelectionModel().getSelectedItem());
            if(ConstanciaDAO.registrarConstancia(constancia)){
                Utilidades.mostrarAlertaSimple("Constancia registrada", "Constancia registrada con éxito", Alert.AlertType.INFORMATION);
                Stage stage = (Stage) cbConstancia.getScene().getWindow();
                stage.close();
            }
        } catch (SQLException ex) {
            Utilidades.mostrarAlertaSimple("Error al registrar la constancia", "Ocurrió un error al registrar la constancia", Alert.AlertType.ERROR);
        }
        
        
    }
    
    private void guardarConstancia(String texto){
        Document document = new Document();
        JFileChooser guardar = new JFileChooser();
        guardar.showSaveDialog(null);
        guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        File archivo = guardar.getSelectedFile();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(archivo));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SolicitarConstanciaFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        document.open();
        document.add(new Paragraph(texto));
        document.close();
    }
    
    private void cargarCbTrabajo(int idDocente){
        try {
            ArrayList<String> arrayTrabajo = TrabajoDAO.recuperarTrabajosPorIdDocente(idDocente);
            cbTrabajo.getItems().setAll(arrayTrabajo);
        } catch (SQLException ex) {
            Logger.getLogger(SolicitarConstanciaFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarCbConstancias(){
        listaConstancias = FXCollections.observableArrayList();
        listaConstancias.addAll("Imparticion", "Jurado");
        cbConstancia.setItems(listaConstancias);
    }
    
    public void inicializarVentana(Docente docenteSesion){
        this.docenteSesion = docenteSesion;
        cargarCbConstancias();
        cbTrabajo.setDisable(true);
        iniciarListeners();
        
    }
    
    public String crearConstancia(){
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
        String fecha = dateFormat.format(new Date());
        
        if(cbConstancia.getSelectionModel().getSelectedItem().equals(("Imparticion"))){
            constanciaTxt = "Región Xalapa\n" +
                "A quien corresponda\n" +
                "El que suscribe, Director de la Facultad de Estadística e Informática, de la Universidad Veracruzana\n" +
                "HACE CONSTAR\n" +
                "que el Mtro. "+docenteSesion.getNombreCompleto()+ ", impartio la experiencia educativa de "+cbTrabajo.getSelectionModel().getSelectedItem().toString()
                        + " en el periodo Agosto 2023 – Enero 2024\n" +
                "A petición de la interesada y para los fines legales que a la misma convenga, se extiende\n" +
                "la presente en la ciudad de Xalapa-Enríquez, Veracruz a los "+fecha+"\n" +
                "                                                             A t e n t a m e n t e\n" +
                "                                                       “Lis de Veracruz: Arte, Ciencia, Luz”\n";
        }
        if(cbConstancia.getSelectionModel().getSelectedItem().equals(("Jurado"))){
            constanciaTxt = "Región Xalapa\n" +
                "A quien corresponda\n" +
                "El que suscribe, Director de la Facultad de Estadística e Informática, de la Universidad Veracruzana\n" +
                "HACE CONSTAR\n" +
                "que el Mtro. "+docenteSesion.getNombreCompleto()+ ", fungió como jurado en los siguientes trabajos recepcionales de la Licenciatura en Ingeniería de Sofware"
                        + " en el periodo Agosto 2023 – Enero 2024\n" +
                "A petición de la interesada y para los fines legales que a la misma convenga, se extiende\n" +
                "la presente en la ciudad de Xalapa-Enríquez, Veracruz a los "+fecha+"\n" +
                "                                                             A t e n t a m e n t e\n" +
                "                                                       “Lis de Veracruz: Arte, Ciencia, Luz”\n";
        }
        
        return constanciaTxt;
    }

    private void iniciarListeners(){
        cbConstancia.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equalsIgnoreCase("Imparticion")){
                cbTrabajo.setDisable(false);
                cargarCbTrabajo(docenteSesion.getIdDocente());
            }else{
                cbTrabajo.setDisable(true);
            }
        });
    }
    
}
