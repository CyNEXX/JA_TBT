/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;


/**
 *
 * @author CyNEXX
 */
public class SDV implements Serializable {

    private Integer idVanzare;
    private Clienti clienti;
    private Produse produse;
    private int idComanda;
    private long cantitateVanzare;
    private double totalProdus;
    private double pretProdus;

    private Integer idClient;
    private String prenumeClient;
    private String numeClient;


    private Double totalSuma;


    public SDV(Integer idVanzare, int idComanda, int cantitateVanzare, Integer idClient, String prenumeClient, String numeClient) {
        this.idVanzare = idVanzare;
        this.idComanda = idComanda;
        this.cantitateVanzare = cantitateVanzare;
        this.idClient = idClient;
        this.prenumeClient = prenumeClient;
        this.numeClient = numeClient;
    }

    public SDV() {
    }

    public Double getTotalSuma() {
        return totalSuma;
    }

    public void setTotalSuma(Double totalSuma) {
        this.totalSuma = totalSuma;
    }

    public Integer getIdVanzare() {
        return idVanzare;
    }

    public void setIdVanzare(Integer idVanzare) {
        this.idVanzare = idVanzare;
    }

    public Clienti getClienti() {
        return clienti;
    }

    public void setClienti(Clienti clienti) {
        this.clienti = clienti;
    }

    public Produse getProduse() {
        return produse;
    }

    public void setProduse(Produse produse) {
        this.produse = produse;
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public long getCantitateVanzare() {
        return cantitateVanzare;
    }

    public void setCantitateVanzare(long cantitateVanzare) {
        this.cantitateVanzare = cantitateVanzare;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getPrenumeClient() {
        return prenumeClient;
    }

    public void setPrenumeClient(String prenumeClient) {
        this.prenumeClient = prenumeClient;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public double getTotalProdus() {
        return totalProdus;
    }

    public void setTotalProdus(double totalProdus) {
        this.totalProdus = totalProdus;
    }

    public double getPretProdus() {
        return pretProdus;
    }

    public void setPretProdus(double pretProdus) {
        this.pretProdus = pretProdus;
    }
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SDV - idClient: ").append(this.idClient);
        sb.append("SDV - numeClient: ").append(this.numeClient);
        sb.append("SDV - prenumeClient: ").append(this.prenumeClient);
        sb.append("SDV - totalSuma: ").append(this.totalSuma);


        return sb.toString();
    }

}
