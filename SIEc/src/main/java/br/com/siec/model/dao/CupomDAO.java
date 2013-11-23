package br.com.siec.model.dao;

import br.com.siec.model.dao.core.DAO;
import br.com.siec.model.persistence.entity.CupomDesconto;
import br.com.siec.model.repository.Cupons;

/**
 * Realiza as operações de transação referentes a Entidade CupomDesconto.
 *
 * @version 1.0.0 17, November 2013.
 * @author Josimar Alves
 */
public class CupomDAO
        extends DAO<CupomDesconto> implements Cupons {

    @Override
    public boolean criarCupom(CupomDesconto cupom) {
        if(isCodigoCupomValido(cupom.getCodigo())){
            return false;
        }
        
        return super.save(cupom);
    }

    @Override
    public boolean alterarCupom(CupomDesconto cupom) {
        return super.update(cupom);
    }

    @Override
    public boolean isCodigoCupomValido(String codigo) {
       if(super.findBy("codigo", codigo) != null){
           return false;
       } 
       
       return true;
    }
}
