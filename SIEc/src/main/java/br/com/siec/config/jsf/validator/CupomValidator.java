package br.com.siec.config.jsf.validator;

import br.com.siec.config.jsf.ViewContext;
import br.com.siec.service.CupomService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import org.springframework.util.StringUtils;

/**
 * Validator para validar a existência do cupom no momento da compra.
 *
 * @version 1.0.0 17 November, 2013.
 * @author Josimar Alves
 */
@ManagedBean(name = "cupomValidator")
@RequestScoped
public class CupomValidator implements Validator {
    
    @Inject
    ViewContext viewContext;
    
    @Inject 
    CupomService cupomService;

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        String codigoDoCupom = (String) value;
        
        if(StringUtils.isEmpty(codigoDoCupom) || codigoDoCupom == null){
            return;
        }
        
        if(!cupomService.isCodigoDoCupomValido(codigoDoCupom)){
            throw new ValidatorException(viewContext.createError("msg_error_cupom"));
        }      
        
    }
}
