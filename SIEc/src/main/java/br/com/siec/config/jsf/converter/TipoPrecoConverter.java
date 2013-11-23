/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.config.jsf.converter;

import br.com.siec.model.persistence.resource.TipoPreco;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.log4j.Logger;

/**
 *
 * @author josimar
 */
@ManagedBean(name = "tipoPrecoConverter")
@RequestScoped
public class TipoPrecoConverter implements Converter {

    Logger logger = Logger.getLogger(TipoPrecoConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {

        if (logger.isDebugEnabled()) {
            logger.debug("{TipoPrecoConverter} Convertendo String para TipoPreco.");
        }
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            
            if (logger.isDebugEnabled()) {
                logger.debug("{TelefoneConverter} String convertida para Telefone: [" + TipoPreco.valueOf(value) + "]");
            }
            
            return TipoPreco.valueOf(value);
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        TipoPreco tipoPreco = (TipoPreco) value;

        if (logger.isDebugEnabled()) {
            logger.debug("{TelefoneConverter} Convertendo Telefone para String.");
        }
        if (tipoPreco == null) {
            return null;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("{TelefoneConverter} Telefone convertido para String: Numero: [" + tipoPreco.toString() + "]");
            }
            return tipoPreco.toString();
        }
    }
}
