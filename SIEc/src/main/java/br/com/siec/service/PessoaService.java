/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service;


import br.com.siec.model.persistence.dao.IPessoaDAO;
import br.com.siec.model.persistence.entity.Pessoa;
import br.com.siec.service.interceptors.Transacional;
import br.com.siec.service.qualifiers.PessoaServiceQualifier;
import br.com.siec.util.factory.AbstractFactory;
import br.com.siec.util.factory.ClassType;
import br.com.siec.util.factory.qualifiers.PessoaFactoryQualifier;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
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
        return this.pessoaFactory.createObject(ClassType.valueOf(classType));
    }
    
    @Override @Transacional
    public boolean save(Pessoa t) {
        if(dao != null){
            System.out.println("Injetado!!!");
        }
        return dao.salve(t);
    }

    @Override @Transacional
    public boolean update(Pessoa t) {
        return dao.update(t);
    }

    @Override @Transacional
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

    @Override @Transacional
    public Pessoa validate(Pessoa t) {
        return dao.validate(t);
    }

    @Override
    public List<Pessoa> findBy(String param, String atribute) {
        return dao.findBy(param, atribute);
    }    

    @Override
    public Pessoa findById(long id, String classType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pessoa> findBy(String param, String atribute, String classType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
