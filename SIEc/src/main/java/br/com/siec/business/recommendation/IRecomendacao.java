/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.business.recommendation;

import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.interfaces.ICliente;
import java.io.Serializable;
import java.util.List;

/**
 * IRecomendacao
 * @version 1.0.0 October 07, 2013.
 * @author Josimar Alves
 */
public interface IRecomendacao extends Serializable {
    
    public List<Produto> recomendarComPreferencias(List<Produto> preferencias);
    
    public List<Produto> recomendar(ICliente cliente);
    
    public List<Produto> recomendarPreferencias();
    
}
