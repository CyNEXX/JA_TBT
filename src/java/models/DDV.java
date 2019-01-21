/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author CyNEXX
 */
public class DDV {

    private String numeProdus;
    private double pretProdus;
    private int cantitateTranz;
    private double sumaProdus;
    private String descriereProdus;
    private int idProdus;
    private int idClient;
    private double totalProdus;




    public DDV() {
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public double getPretProdus() {
        return pretProdus;
    }

    public void setPretProdus(double pretProdus) {
        this.pretProdus = pretProdus;
    }

    public int getCantitateTranz() {
        return cantitateTranz;
    }

    public void setCantitateTranz(int cantitateTranz) {
        this.cantitateTranz = cantitateTranz;
    }

    public double getSumaProdus() {
        return sumaProdus;
    }

    public void setSumaProdus(double sumaProdus) {
        this.sumaProdus = sumaProdus;
    }

    public String getDescriereProdus() {
        return descriereProdus;
    }

    public void setDescriereProdus(String descriereProdus) {
        this.descriereProdus = descriereProdus;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public double getTotalProdus() {
        return totalProdus;
    }

    public void setTotalProdus(double totalProdus) {
        this.totalProdus = totalProdus;
    }
    
    

}
