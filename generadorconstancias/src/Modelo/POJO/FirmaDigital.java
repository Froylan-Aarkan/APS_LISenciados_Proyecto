/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.POJO;

/**
 *
 * @author froyl
 */
public class FirmaDigital {
    int idFirmaDigital;
    byte[] firma;
    
    public FirmaDigital(){
        
    }

    public int getIdFirmaDigital() {
        return idFirmaDigital;
    }

    public byte[] getFirma() {
        return firma;
    }

    public void setIdFirmaDigital(int idFirmaDigital) {
        this.idFirmaDigital = idFirmaDigital;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }
}
