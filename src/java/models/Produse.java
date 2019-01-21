package models;
// Generated Jul 14, 2018 3:30:17 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Produse generated by hbm2java
 */
public class Produse implements java.io.Serializable {

    private Integer idProdus;
    private String numeProdus;
    private String descriereProdus;
    private Integer stocProdus;
    private double pret;
    private Integer totalVandute;
    private Byte produsActiv;
    private Set<Vanzari> vanzaris = new HashSet<Vanzari>(0);

    private int stocDorit = 1;

    public Produse() {
    }

    public Produse(String numeProdus, double pret) {
        this.numeProdus = numeProdus;
        this.pret = pret;
    }

    public Produse(String numeProdus, String descriereProdus, Integer stocProdus, double pret, Integer totalVandute, Set<Vanzari> vanzaris) {
        this.numeProdus = numeProdus;
        this.descriereProdus = descriereProdus;
        this.stocProdus = stocProdus;
        this.pret = pret;
        this.totalVandute = totalVandute;
        this.vanzaris = vanzaris;
    }

    public Produse(Integer idProdus, String numeProdus, String descriereProdus, Integer stocProdus, double pret, Integer totalVandute) {
        this.idProdus = idProdus;
        this.numeProdus = numeProdus;
        this.descriereProdus = descriereProdus;
        this.stocProdus = stocProdus;
        this.pret = pret;
        this.totalVandute = totalVandute;
    }

    public Produse(String numeProdus, String descriereProdus, Integer stocProdus, double pret, Integer totalVandute, Byte produsActiv, Set<Vanzari> vanzaris) {
        this.numeProdus = numeProdus;
        this.descriereProdus = descriereProdus;
        this.stocProdus = stocProdus;
        this.pret = pret;
        this.totalVandute = totalVandute;
        this.produsActiv = produsActiv;
        this.vanzaris = vanzaris;
    }

    public Integer getIdProdus() {
        return this.idProdus;
    }

    public void setIdProdus(Integer idProdus) {
        this.idProdus = idProdus;
    }

    public String getNumeProdus() {
        return this.numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public String getDescriereProdus() {
        return this.descriereProdus;
    }

    public void setDescriereProdus(String descriereProdus) {
        this.descriereProdus = descriereProdus;
    }

    public Integer getStocProdus() {
        return this.stocProdus;
    }

    public void setStocProdus(Integer stocProdus) {
        this.stocProdus = stocProdus;
    }

    public double getPret() {
        return this.pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public Integer getTotalVandute() {
        return this.totalVandute;
    }

    public void setTotalVandute(Integer totalVandute) {
        this.totalVandute = totalVandute;
    }

    public Set<Vanzari> getVanzaris() {
        return this.vanzaris;
    }

    public void setVanzaris(Set<Vanzari> vanzaris) {
        this.vanzaris = vanzaris;
    }

    public int getStocDorit() {
        return stocDorit;
    }

    public void setStocDorit(int stocDorit) {
        this.stocDorit = stocDorit;
    }

    public void addVanzare(Vanzari vnz) {
        this.vanzaris.add(vnz);
    }

    public Byte getProdusActiv() {
        return this.produsActiv;
    }

    public void setProdusActiv(Byte produsActiv) {
        this.produsActiv = produsActiv;
    }

}
