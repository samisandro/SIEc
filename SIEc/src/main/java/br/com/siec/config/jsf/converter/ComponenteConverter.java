/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.config.jsf.converter;

import br.com.siec.facade.ProdutoFacade;
import br.com.siec.model.persistence.entity.Componente;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import org.apache.log4j.Logger;

/**
 * <p> Converter </p> para objeto Componente.
 *
 * @version 1.00 August 17
 * @author josimar
 */
@ManagedBean(name="componenteConverter")
@RequestScoped
public class ComponenteConverter implements Converter{
    
    @Inject
    @ProdutoServiceQualifier
    ProdutoFacade produtoService;
    
    Logger logger = Logger.getLogger(ProdutoConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        
        if (logger.isDebugEnabled()) {
            logger.debug("{ComponenteConverter} Convertendo String para Componente.");
        }
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            long id = Long.parseLong(value);
            if (logger.isDebugEnabled()) {
                logger.debug("{ComponenteConverter} String convertida para Componente: Id:["+id+"]");
            }
            return produtoService.find(id);
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        Componente componente = (Componente) value;
        
        if (logger.isDebugEnabled()) {
            logger.debug("{ComponenteConverter} Convertendo Produto para String.");
        }
        if (componente == null || componente.getId() == 0) {
            return null;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("{ComponenteConverter} Componente convertido para String: Nome: [" + componente.getNome() + "]"
                        + " Codigo:[" + componente.getId() + "]");
            }
            return String.valueOf(componente.getId());
        }
    }
    
}
