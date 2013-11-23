/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.config.jsf.validator;

import br.com.siec.config.jsf.ViewContext;
import br.com.siec.service.ClienteService;
import br.com.siec.service.qualifiers.ClienteServiceQualifier;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author Josimar Alves
 */
@ManagedBean(name = "ageValidator")
@RequestScoped
public class AgeValidator implements Validator {
    
    @Inject
    @ClienteServiceQualifier
    private ClienteService clienteService;
    
    @Inject
    private ViewContext viewContext;

    @Override
    public void validate(FacesContext context,
            UIComponent component, Object value) throws ValidatorException {
        Date birthDay = (Date) value;
        
        if(birthDay == null){
            return;
        }
        
        if(!clienteService.isValidAge(birthDay)){
            throw new ValidatorException(viewContext.createError("msg_error_invalid_age"));
        }
            
    }
}
