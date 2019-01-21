/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import controllers.HintsController;
import controllers.TopController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import controllers.IndexController;
import javax.faces.application.FacesMessage;

/**
 *
 * @author CyNEXX
 */
@FacesValidator("textValidator")
public class Text_validator implements Validator {

    public static Pattern pattern = Pattern.compile(TopController.getAddr_pattern());
    Matcher macher;


    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
        IndexController ic = new IndexController();
        macher = pattern.matcher((String) value);
        if (!macher.matches()) {
            String msg = "Textul introdus este formatat necorespunzator";
            HintsController.setHint(msg);
            System.out.println(msg);
            FacesMessage fmsg = new FacesMessage("Textul introdus este formatat necorespunzator", "Textul introdus este formatat necorespunzator");
            fmsg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(fmsg);
        }
    }
}
