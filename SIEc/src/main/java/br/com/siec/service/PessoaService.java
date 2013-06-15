/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service;


import br.com.siec.model.persistence.dao.IPessoaDAO;
import br.com.siec.model.persistence.entity.Pessoa;
import br.com.siec.service.qualifiers.PessoaServiceQualifier;
import br.com.siec.util.factory.AbstractFactory;
import br.com.siec.util.factory.ClassType;
import br.com.siec.util.factory.qualifiers.PessoaFactoryQualifier;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author josimar
 */
@PessoaServiceQualifier
public class PessoaService implements Service<Pessoa>{
    
    @Inject
    private IPessoaDAO dao;
    
    @Inject @PessoaFactoryQualifier
    private AbstractFactory pessoaFactory;
    
       
    @Override
    public Pessoa create(String classType) {
        return pessoaFactory.createObject(ClassType.valueOf(classType));
    }
    
    @Override
    public boolean save(Pessoa t) {
        if(dao != null){
            System.out.println("Injetado!!!");
        }
        return dao.salve(t);
    }

    @Override
    public boolean update(Pessoa t) {
        return dao.update(t);
    }

    @Override
    public boolean delete(Pessoa t) {
        return dao.delete(t);
    }

    @Override
    public Pessoa findById(long id) {
        return dao.find(id);
    }

    @Override
    public List<Pessoa> listAll() {
        return dao.listAll();
    }

    @Override
    public Pessoa validate(Pessoa t) {
        return dao.validate(t);
    }

    @Override
    public List<Pessoa> findBy(String param, String atribute) {
        return dao.findBy(param, atribute);
    }    
    
}
