package br.com.siec.config.jsf.validator;

import br.com.siec.config.jsf.ViewContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javax.inject.Inject;
import org.springframework.util.StringUtils;

/**
 * <b> Validator </b> para validação da senha/confirmação de senha. *
 * @version 1.00 August 19, 2013
 * @author Josimar
 */
@ManagedBean(name="passwordMatchValidator")
@RequestScoped
public class ConfirmPasswordMatchingValidator implements Validator {

    @Inject
    private ViewContext viewContext;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        String confirmPassword = (String) value;
        String formId = component.getNamingContainer().getId();

        UIInput passwordComponent = (UIInput) context.getViewRoot()
                .findComponent(formId + ":password");
        
        String password = (String) passwordComponent.getLocalValue();
        
        if (StringUtils.isEmpty(password)
                || StringUtils.isEmpty(confirmPassword)) {
            return;
        }

        if (!password.equals(confirmPassword)) {
            throw new ValidatorException(                    
                    viewContext.createError("msg_passwords_match"));
        }
    }
}
