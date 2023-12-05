/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.POJO;

import java.sql.Date;

/**
 *
 * @author froyl
 */
public class Constancia {
    private int idConstancia;
    private int docenteSolicitud;
    private int periodoSolicitud;
    private String descripcion;
    private String tipo;
    private Date fechaSolicitud;

    public Constancia() {
    }

    public int getIdConstancia() {
        return idConstancia;
    }

    public void setIdConstancia(int idConstancia) {
        this.idConstancia = idConstancia;
    }

    public int getDocenteSolicitud() {
        return docenteSolicitud;
    }

    public void setDocenteSolicitud(int docenteSolicitud) {
        this.docenteSolicitud = docenteSolicitud;
    }

    public int getPeriodoSolicitud() {
        return periodoSolicitud;
    }

    public void setPeriodoSolicitud(int periodoSolicitud) {
        this.periodoSolicitud = periodoSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }    
}
