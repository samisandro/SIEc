package br.com.siec.facade;

import br.com.siec.service.interceptors.Transacional;
import br.com.siec.service.qualifiers.ClienteServiceQualifier;

import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ClassType;
import br.com.siec.api.factory.qualifiers.ClienteFactoryQualifier;

import br.com.siec.api.resource.CPFValidator;
import br.com.siec.api.resource.CNPJValidator;
import br.com.siec.api.resource.EncryptData;

import br.com.siec.model.dao.ClienteDAO;
import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.model.persistence.entity.Usuario;
import br.com.siec.model.persistence.resource.TipoUsuario;
import br.com.siec.model.repository.Clientes;
import br.com.siec.model.repository.Usuarios;
import br.com.siec.service.ClienteService;
import br.com.siec.service.UsuarioService;
import br.com.siec.service.qualifiers.UsuarioServiceQualifier;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 * @version 1.00
 * @author Josimar Alves
 */
@ClienteServiceQualifier
public class ClienteFacade implements ClienteService {

    @Inject
    private Clientes clienteDAO;
    
    @Inject
    @ClienteFactoryQualifier
    private AbstractFactory clienteFactory;

    @Override
    public Cliente create(String classType) {
        return this.clienteFactory.createObject(ClassType.valueOf(classType));
    }

    @Override
    @Transacional
    public boolean save(Cliente cliente) {        
        cliente.getUsuario().setTipo(TipoUsuario.CLIENTE);
        cliente.getUsuario().setAtivo(true);
        cliente.getUsuario().setDataCadastro(new Date());
        String password = cliente.getUsuario().getSenha();
        cliente.getUsuario().setSenha(EncryptData.encryptSHA256(password));
        
        if(clienteDAO.save(cliente)){
            return true;
        }
        return false;
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

    @Override
    public boolean isValidCpf(String cpf) {
        return CPFValidator.isValidCPF(cpf);
    }

    @Override
    public boolean isCpfAlreadyInUse(String cpf) {
        return clienteDAO.isCpfAlreadyInUse(CPFValidator.retirarMascara(cpf));
    }

    @Override
    public boolean isValidCnpj(String cnpj) {
        return CNPJValidator.isValidCNPJ(cnpj);
    }

    @Override
    public boolean isCnpjAlreadyInUse(String cnpj) {
        return clienteDAO.isCnpjAlreadyInUse(CNPJValidator.retirarMascara(cnpj));
    }

    @Override
    public boolean isValidBirthday(Date birthday) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
