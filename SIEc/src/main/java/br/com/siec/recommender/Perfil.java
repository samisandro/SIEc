package br.com.siec.recommender;

import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.model.persistence.entity.Produto;
import java.util.List;

/**
 * 
 * @version 1.0.0 September 23, 2013.
 * @author Josimar Alves
 */
public class Perfil {
    
    private List<Produto> preferencias;
    
    private List<Produto> sugestoes;
    
    private Cliente cliente;
    
    public Perfil(Cliente cliente){
        this.cliente = cliente;
    }
    
}
