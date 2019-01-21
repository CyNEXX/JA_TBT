/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import models.Clienti;

/**
 *
 * @author CyNEXX
 */
@ManagedBean(eager = true)
@Named(value = "infoClientController")
@SessionScoped
public class InfoClientController implements Serializable {

    private static Clienti client;

    private static Integer id; //idClient
    private static String pc; //prenumeClient
    private static String nc; //numeClient
    private static String ac; //adresaClient
    private static String tc; //telefonClient
    private static String nv; //nrVanzari
    private static String np; //nrProduse
    private static String cv; //cifraVanzari

    public InfoClientController() {
    }

    public static Clienti getClient() {
        return client;
    }

    public static void setClient(Clienti c) {
        client = c;
        id = client.getIdClient();
        pc = client.getPrenumeClient();
        nc = client.getNumeClient();
        tc = client.getTelefonClient();
        ac = client.getAdresaClient();
        try {
            nv = Integer.toString(client.getNrVanzari());
        } catch (NullPointerException e) {
            nv = "Nicio vanzare";
        }
        try {
            np = Integer.toString(client.getNrProduse());
        } catch (NullPointerException e) {
            np = "Niciun produs";
        }
        try {
            cv = Double.toString(client.getCifraVanzari());
        } catch (NullPointerException e) {
            cv = "0";
        }
    }

    public static void resetClient() {
        client = null;
        id = 0;
        pc = "";
        nc = "";
        ac = "";
        tc = "";
        nv = "";
        np = "";
        cv = "";
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(int id) {
        InfoClientController.id = id;
    }

    public static String getPc() {
        return InfoClientController.pc;
    }

    public static void setPc(String pc) {
        InfoClientController.pc = pc;
    }

    public static String getNc() {
        return nc;
    }

    public static void setNc(String nc) {
        InfoClientController.nc = nc;
    }

    public static String getAc() {
        return InfoClientController.ac;
    }

    public static void setAc(String ac) {
        InfoClientController.ac = ac;
    }

    public static String getTc() {
        return InfoClientController.tc;
    }

    public static void setTc(String tc) {
        InfoClientController.tc = tc;
    }

    public static String getNv() {
        return InfoClientController.nv;
    }

    public static void setNv(String nv) {
        InfoClientController.nv = nv;
    }

    public static String getNp() {
        return InfoClientController.np;
    }

    public static void setNp(String np) {
        InfoClientController.np = np;
    }

    public static String getCv() {
        return InfoClientController.cv;
    }

    public static void setCv(String cv) {
        InfoClientController.cv = cv;
    }


    public static String printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Client ID ").append(id).append(", ")
                .append("Client Nume ").append(nc).append(", ")
                .append("Client Prenume ").append(pc).append(", ")
                .append("Client Adresa ").append(ac).append(", ")
                .append("Client Telefon ").append(tc).append(".");
        return sb.toString();
    }

}
