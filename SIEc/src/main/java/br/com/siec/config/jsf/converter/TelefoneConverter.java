/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.config.jsf.converter;

import br.com.siec.model.persistence.entity.Telefone;
import br.com.siec.model.persistence.interfaces.ITelefone;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.log4j.Logger;

/**
 * <p> Converter </p> para objeto Usuario.
 *
 * @version 1.00 August 17
 * @author josimar
 */
@ManagedBean(name = "telefoneConverter")
@RequestScoped
public class TelefoneConverter implements Converter {

    Logger logger = Logger.getLogger(TelefoneConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {

        if (logger.isDebugEnabled()) {
            logger.debug("{TelefoneConverter} Convertendo String para Telefone.");
        }
        if (value == null || value.isEmpty()) {
            return null;
        } else {

            System.out.println("Telefone Antes da Conversão:" + value);
            ITelefone phone = converteStringToTelefone(value);
            
            if (logger.isDebugEnabled()) {
                logger.debug("{TelefoneConverter} String convertida para Telefone: [" + phone.toString() + "]");
            }
            return phone;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        Telefone telefone = (Telefone) value;

        if (logger.isDebugEnabled()) {
            logger.debug("{TelefoneConverter} Convertendo Telefone para String.");
        }
        if (telefone == null) {
            return null;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("{TelefoneConverter} Telefone convertido para String: Numero: [" + telefone.toString() + "]");
            }
            return telefone.toString();
        }
    }

    public ITelefone converteStringToTelefone(String telefoneComMascara) {
        /**
         * String deve estar no formato: (11) 1111-1111.
         */
        String ddd = telefoneComMascara.substring(1, 3);
        
        String numero = telefoneComMascara.substring(5, 9);        
               numero += telefoneComMascara.substring(10, 14);
        
        ITelefone telefone = new Telefone();
        telefone.setDdd(ddd);
        telefone.setNumero(numero);
        
        return telefone;
    }
}
