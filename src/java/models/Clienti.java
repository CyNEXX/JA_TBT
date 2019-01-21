package models;
// Generated Jul 14, 2018 3:30:17 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Clienti generated by hbm2java
 */
public class Clienti implements java.io.Serializable {

    private Integer idClient;
    private String prenumeClient;
    private String numeClient;
    private String adresaClient;
    private String telefonClient;
    private Integer nrVanzari;
    private Integer nrProduse;
    private Double cifraVanzari;
    private Byte clientActiv;
    private Set<Vanzari> vanzaris = new HashSet<>(0);

    public Clienti() {
    }

    public Clienti(String numeClient, String prenumeClient, String adresaClient, String telefonClient) {
        this.prenumeClient = prenumeClient;
        this.numeClient = numeClient;
        this.adresaClient = adresaClient;
        this.telefonClient = telefonClient;
    }

    public Clienti(String prenumeClient, String numeClient, String adresaClient, String telefonClient, Integer nrVanzari, Integer nrProduse, Double cifraVanzari, Set<Vanzari> vanzaris) {
        this.prenumeClient = prenumeClient;
        this.numeClient = numeClient;
        this.adresaClient = adresaClient;
        this.telefonClient = telefonClient;
        this.nrVanzari = nrVanzari;
        this.nrProduse = nrProduse;
        this.cifraVanzari = cifraVanzari;
        this.vanzaris = vanzaris;
    }

    public Clienti(Integer idClient, String numeClient, String prenumeClient, String adresaClient, String telefonClient, Integer nrVanzari, Integer nrProduse, Double cifraVanzari) {
        this.idClient = idClient;
        this.prenumeClient = prenumeClient;
        this.numeClient = numeClient;
        this.adresaClient = adresaClient;
        this.telefonClient = telefonClient;
        this.nrVanzari = nrVanzari;
        this.nrProduse = nrProduse;
        this.cifraVanzari = cifraVanzari;
    }

    public Clienti(String prenumeClient, String numeClient, String adresaClient, String telefonClient, Integer nrVanzari, Integer nrProduse, Double cifraVanzari, Byte clientActiv, Set<Vanzari> vanzaris) {
        this.prenumeClient = prenumeClient;
        this.numeClient = numeClient;
        this.adresaClient = adresaClient;
        this.telefonClient = telefonClient;
        this.nrVanzari = nrVanzari;
        this.nrProduse = nrProduse;
        this.cifraVanzari = cifraVanzari;
        this.clientActiv = clientActiv;
        this.vanzaris = vanzaris;
    }

    public Integer getIdClient() {
        return this.idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getPrenumeClient() {
        return this.prenumeClient;
    }

    public void setPrenumeClient(String prenumeClient) {
        this.prenumeClient = prenumeClient;
    }

    public String getNumeClient() {
        return this.numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getAdresaClient() {
        return this.adresaClient;
    }

    public void setAdresaClient(String adresaClient) {
        this.adresaClient = adresaClient;
    }

    public String getTelefonClient() {
        return this.telefonClient;
    }

    public void setTelefonClient(String telefonClient) {
        this.telefonClient = telefonClient;
    }

    public Integer getNrVanzari() {
        return this.nrVanzari;
    }

    public void setNrVanzari(Integer nrVanzari) {
        this.nrVanzari = nrVanzari;
    }

    public Integer getNrProduse() {
        return this.nrProduse;
    }

    public void setNrProduse(Integer nrProduse) {
        this.nrProduse = nrProduse;
    }

    public Double getCifraVanzari() {
        return this.cifraVanzari;
    }

    public void setCifraVanzari(Double cifraVanzari) {
        this.cifraVanzari = cifraVanzari;
    }

    public Byte getClientActiv() {
        return this.clientActiv;
    }

    public void setClientActiv(Byte clientActiv) {
        this.clientActiv = clientActiv;
    }

    public Set<Vanzari> getVanzaris() {
        return this.vanzaris;
    }

    public void setVanzaris(Set<Vanzari> vanzaris) {
        this.vanzaris = vanzaris;
    }

    public void addVanzare(Vanzari vnz) {
        this.vanzaris.add(vnz);
    }

}
