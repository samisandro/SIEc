package br.com.siec.config.jsf.validator;

import br.com.siec.config.jsf.ViewContext;
import br.com.siec.service.UsuarioService;
import br.com.siec.service.qualifiers.UsuarioServiceQualifier;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import org.springframework.util.StringUtils;

/**
 * @version 1.0.0 August 12, 2013.
 * @author Josimar Alves
 */
@ManagedBean(name = "recoveryValidator")
@RequestScoped
public class RecoveryValidator implements Validator{
    
    @Inject
    private ViewContext viewContext;
    
    @Inject
    @UsuarioServiceQualifier
    private UsuarioService userFacade;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        String email = (String) value;

        if (StringUtils.isEmpty(email) || email == null) {
            return;
        }

        if (!userFacade.isEmailValid(email)) {
            throw new ValidatorException(viewContext.createError("msg_error_invalid_email"));
        }
        if (!userFacade.isEmailAlredyInUse(email)) {
            throw new ValidatorException(viewContext.createError("msg_recovery_email_bad_email"));
        }
    }
    
}
