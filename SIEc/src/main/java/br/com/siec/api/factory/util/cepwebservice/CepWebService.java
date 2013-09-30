package br.com.siec.api.factory.util.cepwebservice;

import br.com.siec.model.persistence.entity.Endereco;

/**
 * @version 1.0.0 September 28, 2013.
 * @author Josimar Alves
 */
public interface CepWebService {
    
    public Endereco loadByCep(String cep);
    
}
