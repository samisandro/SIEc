/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.config.jsf.converter;

import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.service.impl.ProdutoServiceImpl;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import org.apache.log4j.Logger;

/**
 * <p> Converter </p> para objeto Produto.
 *
 * @version 1.00 August 17
 * @author josimar
 */
@ManagedBean(name="produtoConverter")
@RequestScoped
public class ProdutoConverter implements Converter {

    @Inject
    @ProdutoServiceQualifier
    ProdutoServiceImpl produtoService;
    
    Logger logger = Logger.getLogger(ProdutoConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        
        if (logger.isDebugEnabled()) {
            logger.debug("{ProdutoConverter} Convertendo String para Produto.");
        }
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            long id = Long.parseLong(value);
            if (logger.isDebugEnabled()) {
                logger.debug("{ProdutoConverter} String convertida para Produto: Id:["+id+"]");
            }
            return produtoService.find(id);
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        Produto produto = (Produto) value;
        
        if (logger.isDebugEnabled()) {
            logger.debug("{ProdutoConverter} Convertendo Produto para String.");
        }
        if (produto == null || produto.getId() == 0) {
            return null;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("{ProdutoConverter} Produto convertido para String: Nome: [" + produto.getNome() + "]"
                        + " Codigo:[" + produto.getId() + "]");
            }
            return String.valueOf(produto.getId());
        }
    }
}
