/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package generadorconstancias;

import Modelo.DAO.DocenteDAO;
import Modelo.DAO.PersonalAdministrativoDAO;
import Modelo.POJO.Docente;
import Modelo.POJO.PersonalAdministrativo;
import Utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author froyl
 */
public class InicioSesionFXMLControlador implements Initializable {
    
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField pfContrasenia;
    @FXML
    private Label lbErrorUsuario;
    @FXML
    private Label lbErrorContrasenia;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) {
        try {
            if(camposValidos()){
                Docente docenteSesion = DocenteDAO.iniciarSesion(tfUsuario.getText(), pfContrasenia.getText());
                
                if(docenteSesion != null){
                    Utilidades.mostrarAlertaSimple("Bienvenid@", "Bienvenid@ " + docenteSesion.getNombreCompleto() + ".", AlertType.INFORMATION);
                    abrirMenuPrincipal(docenteSesion);
                }else{
                    PersonalAdministrativo personalSesion = PersonalAdministrativoDAO.iniciarSesion(tfUsuario.getText(), pfContrasenia.getText());
                    if(personalSesion != null){
                        Utilidades.mostrarAlertaSimple("Bienvenid@", "Bienvenid@ " + personalSesion.getNombreCompleto() + ".", AlertType.INFORMATION);
                        abrirMenuPrincipal(personalSesion);
                    }else{
                        Utilidades.mostrarAlertaSimple("Usuario incorrecto", "El correo institucional y/o contraseña es incorrecto, favor de verificar", AlertType.WARNING);
                    }
                }              
            }            
        } catch (SQLException | NullPointerException e) {
            Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salió mal: " + e.getMessage(), AlertType.ERROR);
        }
    }
    
    private void abrirMenuPrincipal(Docente docenteSesion){
        try {
            FXMLLoader loaderVentanaPrincipal = new FXMLLoader(getClass().getResource("MenuPrincipalFXML.fxml"));
            Parent ventanaPrincipal = loaderVentanaPrincipal.load();
            MenuPrincipalFXMLControlador controladorVentanaPrincipal = loaderVentanaPrincipal.getController();
            controladorVentanaPrincipal.iniciarVentanaDocente(docenteSesion);
            Scene escenaVentanaPrincipal = new Scene(ventanaPrincipal);
            Stage stageVentanaPrincipal = (Stage) tfUsuario.getScene().getWindow();
            stageVentanaPrincipal.setScene(escenaVentanaPrincipal);
            stageVentanaPrincipal.show();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salió mal: " + e.getMessage(), AlertType.ERROR);
        }
    }
    
    private void abrirMenuPrincipal(PersonalAdministrativo personalSesion){
        try {
            FXMLLoader loaderVentanaPrincipal = new FXMLLoader(getClass().getResource("MenuPrincipalFXML.fxml"));
            Parent ventanaPrincipal = loaderVentanaPrincipal.load();
            MenuPrincipalFXMLControlador controladorVentanaPrincipal = loaderVentanaPrincipal.getController();
            controladorVentanaPrincipal.iniciarVentanaPersonalAdministrativo(personalSesion);
            Scene escenaVentanaPrincipal = new Scene(ventanaPrincipal);
            Stage stageVentanaPrincipal = (Stage) tfUsuario.getScene().getWindow();
            stageVentanaPrincipal.setScene(escenaVentanaPrincipal);
            stageVentanaPrincipal.show();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Algo salió mal", "Algo salió mal: " + e.getMessage(), AlertType.ERROR);
        }
    }
    
    private boolean camposValidos(){
        boolean sonValidos = true;
        
        if(tfUsuario.getText().equals("")){
            lbErrorUsuario.setText("Debe ingresar un correo institucional.");
            sonValidos = false;
        }else{           
            lbErrorUsuario.setText("");
        }
                
        if(pfContrasenia.getText().equals("")){
            lbErrorContrasenia.setText("Debe ingresar su contraseña");
            sonValidos = false;
        }else{
            lbErrorContrasenia.setText("");
        }
        
        return sonValidos;
    }
}
