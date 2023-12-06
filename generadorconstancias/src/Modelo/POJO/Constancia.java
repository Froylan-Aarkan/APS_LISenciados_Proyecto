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
    private int idFirmaDigital;
    private String descripcion;
    private String tipo;
    private String periodo;
    private Date fechaSolicitud;
    private byte[] firma;

    public Constancia() {
    }

    public byte[] getFirma() {
        return firma;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }

    public int getIdFirmaDigital() {
        return idFirmaDigital;
    }

    public void setIdFirmaDigital(int idFirmaDigital) {
        this.idFirmaDigital = idFirmaDigital;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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
