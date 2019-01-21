/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import controllers.HintsController;
import controllers.VanzariController;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import models.Produse;

/**
 *
 * @author CyNEXX
 */
@FacesValidator("stocValidator")
public class Stoc_validator implements Validator {
    
    DataModel listDMCos = VanzariController.getStaticDMList();
    Produse produs;
    
    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object input) throws ValidatorException {
        produs = (Produse) listDMCos.getRowData();
        System.out.println("Stoc validator: Stoc introdus (Integer) - " + (int) input + ". Stoc Produs - " + produs.getStocProdus() + ". Value is: " + input);
        if ((int) input > produs.getStocProdus()) {
            HintsController.setHint("Cantitatea depaseste stocul");
            FacesMessage fmsg = new FacesMessage("Cantitatea depaseste stocul", "Cantitatea dorita depaseste stocul disponibil");
            fmsg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(fmsg);
        }
        
        if (produs.getStocDorit() < 1) {
            HintsController.setHint("Cantitatea dorita este 0");
            FacesMessage fmsg = new FacesMessage("Cantitatea dorita este 0", "Pentru eliminarea produsului, folositi butonul aferent.");
            fmsg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(fmsg);
        }
    }
    
}
