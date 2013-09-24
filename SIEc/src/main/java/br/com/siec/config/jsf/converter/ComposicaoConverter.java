/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.config.jsf.converter;

import br.com.siec.facade.ProdutoFacade;
import br.com.siec.model.persistence.entity.Composicao;
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
@ManagedBean(name="composicaoConverter")
@RequestScoped
public class ComposicaoConverter implements Converter{
    
    @Inject
    @ProdutoServiceQualifier
    ProdutoFacade produtoService;
    
    Logger logger = Logger.getLogger(ComposicaoConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        
        if (logger.isDebugEnabled()) {
            logger.debug("{ComposicaoConverter} Convertendo String para Composicao.");
        }
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            long id = Long.parseLong(value);
            if (logger.isDebugEnabled()) {
                logger.debug("{ComposicaoConverter} String convertida para Composicao: Id:["+id+"]");
            }
            return produtoService.find(id);
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        Composicao composicao = (Composicao) value;
        
        if (logger.isDebugEnabled()) {
            logger.debug("{ComposicaoConverter} Convertendo Composicao para String.");
        }
        if (composicao == null || composicao.getId() == 0) {
            return null;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("{ComposicaoConverter} Composicao convertido para String: Nome: [" + composicao.getNome() + "]"
                        + " Codigo:[" + composicao.getId() + "]");
            }
            return String.valueOf(composicao.getId());
        }
    }
    
}
