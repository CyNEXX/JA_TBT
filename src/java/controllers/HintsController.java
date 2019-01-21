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
@Named("hintsController")
@RequestScoped
public class HintsController implements Serializable {

    private static String msg = "";
    private static boolean visible = false;

    public HintsController() {
        msg = "";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String hint) {
        msg = hint;
    }

    public static void setHint(String hint) {
        msg = hint;
    }

    public void setVisibleHint(boolean exp) {
        visible = exp;
    }

    public boolean isVisible() {
        return visible;
    }
        

}
