/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service.impl;

import br.com.siec.business.code_generator.CodeGenerator;
import br.com.siec.business.code_generator.Mode;
import br.com.siec.model.persistence.entity.CupomDesconto;
import br.com.siec.model.repository.Cupons;
import br.com.siec.service.CupomService;
import javax.inject.Inject;

/**
 * Facade com as operações e regras de negocio, referentes ao Cupom de Desconto.
 * @version 1.0.0 17 November, 2013
 * @author Josimar Alves
 */
public class CupomServiceImpl implements CupomService {
    
    @Inject
    Cupons cupomRepository;
    
    @Inject
    CodeGenerator geradorDeCodigo;

    @Override
    public boolean criarCupom(CupomDesconto cupom) {
       return cupomRepository.criarCupom(cupom);
    }

    @Override
    public String gerarCodigoDoCupom() {
        String codigo = geradorDeCodigo.generateCode(5, Mode.UPPERCASE_ALPHANUMERIC);
        
        if(isCodigoDoCupomValido(codigo)){
            return codigo;
        } else {
           return gerarCodigoDoCupom();
        }
    }

    @Override
    public boolean isCodigoDoCupomValido(String codigoCupom) {
        return cupomRepository.isCodigoCupomValido(codigoCupom);
    }

    @Override
    public boolean alterarCupom(CupomDesconto cupom) {
        return cupomRepository.alterarCupom(cupom);
    }
    
}
