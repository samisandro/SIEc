package br.com.siec.config.jsf.validator;

import br.com.siec.config.jsf.ViewContext;
import br.com.siec.service.ClienteService;
import br.com.siec.service.qualifiers.ClienteServiceQualifier;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import org.springframework.util.StringUtils;

/**
 * @version 1.0.0 September 28, 2013.
 * @author Josimar Alves
 */
@ManagedBean(name="cnpjValidator")
@RequestScoped
public class CNPJValidator implements Validator{
    
    @Inject
    @ClienteServiceQualifier
    private ClienteService clienteService;
    
    @Inject
    ViewContext viewContext;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        String cnpj = (String) value;
        
        if(StringUtils.isEmpty(cnpj) || cnpj == null){
            return;
        }
        
        if (!clienteService.isValidCnpj(cnpj)) {
            throw new ValidatorException(viewContext.createError("msg_error_invalid_cnpj"));
        }
        if (clienteService.isCnpjAlreadyInUse(cnpj)) {
            throw new ValidatorException(viewContext.createError("msg_error_already_cnpj"));
        }
    }
}
