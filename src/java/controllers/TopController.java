/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author CyNEXX
 */
@ManagedBean(eager = true)
@Named(value = "topController")
@RequestScoped
public class TopController implements Serializable {

    public static String name_pattern = "^[A-Za-z -]+$";
    public static String stoc_pattern = "^[1-9]+[0-9]*$";
    public static String addr_pattern = "^[^\\s][A-Za-z0-9 .,-]+$";
    public static String tel_pattern = "^[0][0-9]{9,}$";
    public static String pret_pattern = "^[0-9]+[\\.]?[0-9]{0,2}$";
    private static String backpage = "";

    public TopController() {
    }

    public static String getPaginaClienti() {
        resetStatics();
        return "index";
    }

    public static String getPaginaProduse() {
        resetStatics();
        return "produse";
    }

    public static String getPaginaVanzari() {
        resetStatics();
        return "vanzari";
    }

    public static String getPaginaVanzarev() {
        resetStatics();
        return "vanzarev";
    }
    
    public static void resetStatics() {
        IndexController.resetStaticFields();
        ProduseController.resetStaticFields();
        VanzariController.resetStaticFields();
        InfoClientController.resetClient();
    }

    public static String getName_pattern() {
        return name_pattern;
    }

    public static void setName_pattern(String name_pattern) {
        TopController.name_pattern = name_pattern;
    }

    public static String getStoc_pattern() {
        return stoc_pattern;
    }

    public static void setStoc_pattern(String stoc_pattern) {
        TopController.stoc_pattern = stoc_pattern;
    }

    public static String getAddr_pattern() {
        return addr_pattern;
    }

    public static void setAddr_pattern(String addr_pattern) {
        TopController.addr_pattern = addr_pattern;
    }

    public static String getTel_pattern() {
        return tel_pattern;
    }

    public static void setTel_pattern(String tel_pattern) {
        TopController.tel_pattern = tel_pattern;
    }

    public static String getPret_pattern() {
        return pret_pattern;
    }

    public static void setPret_pattern(String pret_pattern) {
        TopController.pret_pattern = pret_pattern;
    }

    public String getBackpage() {

        if (backpage.contentEquals("index")) {
            return getPaginaClienti();
        }
        if (backpage.contentEquals("vanzarev")) {
            return getPaginaVanzarev();
        }
        return getPaginaVanzari();
    }

    public static void setBackpage(String backpage) {
        TopController.backpage = backpage;
    }

}
