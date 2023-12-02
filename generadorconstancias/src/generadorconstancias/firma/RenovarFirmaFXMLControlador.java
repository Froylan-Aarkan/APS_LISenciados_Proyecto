/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package generadorconstancias.firma;

import Modelo.DAO.FirmaDigitalDAO;
import Modelo.POJO.PersonalAdministrativo;
import Utilidades.Utilidades;
import generadorconstancias.MenuPrincipalFXMLControlador;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author froyl
 */
public class RenovarFirmaFXMLControlador implements Initializable {
    private PersonalAdministrativo personalSesion;
    private File archivoFirma;
    
    @FXML
    private ImageView ivFirma;
    @FXML
    private Label lbNoFirmas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarFirma();
    }    

    @FXML
    private void regresar(ActionEvent event) {
        try {
            FXMLLoader loaderMenuPrincipal = new FXMLLoader(getClass().getResource("/generadorconstancias/MenuPrincipalFXML.fxml"));
            Parent menuPrincipal = loaderMenuPrincipal.load();
            MenuPrincipalFXMLControlador controlador = loaderMenuPrincipal.getController();
            Scene escenaMenuPrincipal = new Scene(menuPrincipal);
            Stage stageMenuPrincipal = (Stage) ivFirma.getScene().getWindow();
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
    private void subirImagen(ActionEvent event) {
        FileChooser dialogoFirma = new FileChooser();
        dialogoFirma.setTitle("Seleccionar firma");
        String[] formatosImagen = {"*.JPG", "*.PNG", "*.JPEG"};
        FileChooser.ExtensionFilter filtroFirma = new FileChooser.ExtensionFilter("Archivos de imagen", formatosImagen);
        dialogoFirma.getExtensionFilters().add(filtroFirma);
        Stage stage = (Stage) ivFirma.getScene().getWindow();
        archivoFirma = dialogoFirma.showOpenDialog(stage);
        
        if(archivoFirma != null){
            try{
                BufferedImage bufferImagen = ImageIO.read(archivoFirma);
                Image imagenFirma = SwingFXUtils.toFXImage(bufferImagen, null);
                ivFirma.setImage(imagenFirma);
                lbNoFirmas.setVisible(false);
            } catch (IOException e) {
                Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salio mal: " + e.getMessage() + ".", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void guardarFirma(ActionEvent event) {
        //TODO validar que no sea una imagen repetida del ultimo registro
        try {
            if(archivoFirma != null && ivFirma.getImage() != null){                
                if(Utilidades.mostrarDialogoConfirmacion("Guardar firma", "¿Desea guardar la firma?")){
                    if(FirmaDigitalDAO.guardarFirma(archivoFirma)){
                        Utilidades.mostrarAlertaSimple("Firma guardada", "Se guardó la firma con éxito.", Alert.AlertType.ERROR);
                    }else{
                        Utilidades.mostrarAlertaSimple("Firma no guardada", "No se logró guardar la firma.", Alert.AlertType.ERROR);
                    }
                }else{
                    cargarFirma();
                }
            }else{
                Utilidades.mostrarAlertaSimple("Firma no subida", "No se ha subido la firma a guardar.", Alert.AlertType.WARNING);
            }         
        } catch (SQLException e) {
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
                Stage stageInicioSesion = (Stage) ivFirma.getScene().getWindow();
                stageInicioSesion.setScene(escenaIniciarSesion);
                stageInicioSesion.setResizable(false);
                stageInicioSesion.setTitle("Iniciar Sesión");
                stageInicioSesion.show();
            } catch (IOException e) {
                Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salio mal: " + e.getMessage() + ".", Alert.AlertType.ERROR);
            }
        }
    }
    
    private void cargarFirma(){
        try {
            byte[] bytesFirma = FirmaDigitalDAO.recuperarUltimaFirma();
            
            if(bytesFirma != null){
                lbNoFirmas.setVisible(false);
                Image firma = new Image(new ByteArrayInputStream(bytesFirma));
                ivFirma.setImage(firma);
            }else{
                lbNoFirmas.setVisible(true);
                archivoFirma = null;
            }
        } catch (SQLException e) {
            Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salio mal: " + e.getMessage() + ".", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
    public void inicializarVentana(PersonalAdministrativo personalSesion){
        this.personalSesion = personalSesion;
    }
}
