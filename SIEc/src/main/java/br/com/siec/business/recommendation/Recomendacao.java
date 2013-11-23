/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.business.recommendation;

import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.interfaces.ICliente;
import br.com.siec.model.repository.Produtos;
import br.com.siec.service.ProdutoService;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;
import java.util.List;
import javax.inject.Inject;

/**
 * @version 1.0.0 October 07, 2013.
 * @author Josimar Alves
 */
public class Recomendacao implements IRecomendacao {
    
    List<Produto> preferencias;
    
    @Inject
    @ProdutoServiceQualifier
    private ProdutoService produtoFacade;

    @Override
    public List<Produto> recomendarComPreferencias(List<Produto> preferencias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produto> recomendar(ICliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produto> recomendarPreferencias() {
       preferencias = produtoFacade.getProductsSoldLess(5);
       preferencias.addAll(produtoFacade.getTopSellingProducts(5));
       
       return preferencias;
    }
    
    
}
