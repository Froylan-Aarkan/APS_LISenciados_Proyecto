/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.POJO;

/**
 *
 * @author froyl
 */
public class PersonalAdministrativo {
    private int idPersonalAdministrativo;
    private String usuario;
    private String contrasenia;
    private String nombreCompleto;
    private String correoInstitucional;
    private String numeroTelefonico;

    public PersonalAdministrativo() {
    }

    public int getIdPersonalAdministrativo() {
        return idPersonalAdministrativo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setIdPersonalAdministrativo(int idPersonalAdministrativo) {
        this.idPersonalAdministrativo = idPersonalAdministrativo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }    

    @Override
    public String toString() {
        return this.nombreCompleto;
    }
}
