/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service;

import br.com.siec.model.persistence.dao.IGenericDAO;
import br.com.siec.model.persistence.entity.IPessoa;

/**
 *
 * @author josimar
 */
public class PessoaService extends Service<IPessoa> implements IPessoaService {
    
    public PessoaService(IGenericDAO dao){
        super(dao);
    }
    
    
}
