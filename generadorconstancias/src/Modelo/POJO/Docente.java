/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.POJO;

import java.util.Objects;

/**
 *
 * @author froyl
 */
public class Docente {
    private int idDocente;
    private int noPersonal;
    private String usuario;
    private String contrasenia;
    private String nombreCompleto;
    private String correoInstitucional;
    private String numeroTelefonico;

    public Docente() {
    }

    public int getIdDocente() {
        return idDocente;
    }

    public int getNoPersonal() {
        return noPersonal;
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

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public void setNoPersonal(int noPersonal) {
        this.noPersonal = noPersonal;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.idDocente;
        hash = 97 * hash + this.noPersonal;
        hash = 97 * hash + Objects.hashCode(this.usuario);
        hash = 97 * hash + Objects.hashCode(this.contrasenia);
        hash = 97 * hash + Objects.hashCode(this.nombreCompleto);
        hash = 97 * hash + Objects.hashCode(this.correoInstitucional);
        hash = 97 * hash + Objects.hashCode(this.numeroTelefonico);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Docente other = (Docente) obj;
        if (this.idDocente != other.idDocente) {
            return false;
        }
        if (this.noPersonal != other.noPersonal) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.contrasenia, other.contrasenia)) {
            return false;
        }
        if (!Objects.equals(this.nombreCompleto, other.nombreCompleto)) {
            return false;
        }
        if (!Objects.equals(this.correoInstitucional, other.correoInstitucional)) {
            return false;
        }
        return Objects.equals(this.numeroTelefonico, other.numeroTelefonico);
    }
}
