/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.config.jsf.converter;

import br.com.siec.model.persistence.entity.Usuario;
import br.com.siec.service.UsuarioService;
import br.com.siec.service.qualifiers.UsuarioServiceQualifier;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import javax.inject.Inject;

import org.apache.log4j.Logger;

/**
 * <p> Converter </p> para objeto Usuario.
 *
 * @version 1.00 August 17
 * @author josimar
 */
@ManagedBean(name = "usuarioConverter")
@RequestScoped
public class UsuarioConverter implements Converter {

    @Inject
    @UsuarioServiceQualifier
    UsuarioService usuarioService;
    
    Logger logger = Logger.getLogger(UsuarioConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {

        if (logger.isDebugEnabled()) {
            logger.debug("{UsuarioConverter} Convertendo String para Usuario.");
        }
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            long id = Long.parseLong(value);
            if (logger.isDebugEnabled()) {
                logger.debug("{UsuarioConverter} String convertida para Usuario: Id:[" + id + "]");
            }
            return usuarioService.find(id);
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        Usuario usuario = (Usuario) value;

        if (logger.isDebugEnabled()) {
            logger.debug("{UsuarioConverter} Convertendo Usuario para String.");
        }
        if (usuario == null || usuario.getId() == 0) {
            return null;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("{UsuarioConverter} Usuario convertido para String: Nome: [" + usuario.getPessoa().getNome() + "]"
                        + " Codigo:[" + usuario.getId() + "]");
            }
            return String.valueOf(usuario.getId());
        }
    }
}
