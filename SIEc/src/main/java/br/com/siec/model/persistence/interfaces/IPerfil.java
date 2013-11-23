/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.persistence.interfaces;

import br.com.siec.model.persistence.entity.Produto;
import java.io.Serializable;
import java.util.Collection;

/**
 * IPerfil
 * @version 1.0.0 October 07, 2013.
 * @author Josimar Alves
 */
public interface IPerfil extends Serializable {
    
    public void serCliente(ICliente cliente);
    
    public ICliente getCliente();
    
    public void addPreferencia(Produto preferencia);
    
    public void addSugestao(Produto sugestao);
    
    public Collection<Produto> getPreferencias();
    
    public Collection<Produto> getSugestoes();
    
}
