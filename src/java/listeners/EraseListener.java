/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import controllers.IndexController;
import helpers.ClientiHelper;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.model.DataModel;
import models.Clienti;

/**
 *
 * @author CyNEXX
 */
public class EraseListener implements ActionListener {

    Clienti client;
    IndexController ic = (IndexController) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("indexController");
    DataModel clientList = ic.getClientTitles();

    @Override
    public void processAction(ActionEvent event)
            throws AbortProcessingException {
        client = (Clienti) clientList.getRowData();
        int nr = client.getIdClient();
        ClientiHelper clientiHelper = new ClientiHelper();
        clientiHelper.dezactiveazaClient(nr);

    }

}
