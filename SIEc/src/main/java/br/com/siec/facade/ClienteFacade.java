package br.com.siec.facade;

import br.com.siec.service.interceptors.Transacional;
import br.com.siec.service.qualifiers.ClienteServiceQualifier;

import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ClassType;
import br.com.siec.api.factory.qualifiers.PessoaFactoryQualifier;
import br.com.siec.model.dao.ClienteDAO;
import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.service.ClienteService;
import java.util.List;
import javax.inject.Inject;

/**
 * @version 1.00
 * @author Josimar Alves
 */
@ClienteServiceQualifier
public class ClienteFacade implements ClienteService {

    @Inject
    private ClienteDAO clienteDAO;
    
    @Inject
    @PessoaFactoryQualifier
    private AbstractFactory pessoaFactory;

    @Override
    public Cliente create(String classType) {
        return this.pessoaFactory.createObject(ClassType.valueOf(classType));
    }

    @Override
    @Transacional
    public boolean save(Cliente t) {
        return clienteDAO.save(t);
    }

    @Override
    @Transacional
    public boolean update(Cliente t) {
        return clienteDAO.update(t);
    }

    @Override
    @Transacional
    public boolean delete(Cliente t) {
        return clienteDAO.delete(t);
    }

    @Override
    @Transacional
    public List<Cliente> listAll() {
        return clienteDAO.listAll();
    }

    @Override
    @Transacional
    public List<Cliente> findBy(String param, String atribute) {
        return clienteDAO.findBy(param, atribute);
    }

    @Override
    @Transacional
    public Cliente find(Long id) {
       return clienteDAO.find(id);
    }

    @Override
    public Long getQuantityOfClients() {
        return clienteDAO.getQuantityOfClients();
    }
}
