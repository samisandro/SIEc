package br.com.siec.service.impl;

import br.com.siec.business.validator.AgeValidator;
import br.com.siec.service.interceptors.Transacional;
import br.com.siec.service.qualifiers.ClienteServiceQualifier;

import br.com.siec.factory.AbstractFactory;
import br.com.siec.factory.ClassType;
import br.com.siec.factory.qualifiers.ClienteFactoryQualifier;

import br.com.siec.business.validator.CPFValidator;
import br.com.siec.business.validator.CNPJValidator;
import br.com.siec.resource.EncryptData;

import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.model.persistence.resource.TipoUsuario;

import br.com.siec.model.repository.Clientes;
import br.com.siec.resource.notification.Notification;
import br.com.siec.resource.notification.NotificationType;
import br.com.siec.resource.notification.qualifiers.CustomerNotificationQualifier;

import br.com.siec.service.ClienteService;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletRequest;

/**
 * @version 1.00
 * @author Josimar Alves
 */
@ClienteServiceQualifier
public class ClienteServiceImpl implements ClienteService {

    @Inject
    private Clientes clienteRepository;
    
    @Inject
    @CustomerNotificationQualifier
    private Notification customerNotification;
    
    @Inject
    @ClienteFactoryQualifier
    private AbstractFactory clienteFactory;
    
    @Inject
    private ServletRequest servletRequest;

    @Override
    public Cliente create(String classType) {
        return this.clienteFactory.createObject(ClassType.valueOf(classType));
    }

    @Override
    @Transacional
    public boolean save(Cliente customer) {        
        
        customerNotification.sendNotification(customer, NotificationType.NEW_ACCOUNT, servletRequest);
        
        customer.getUsuario().setTipo(TipoUsuario.CLIENTE);
        customer.getUsuario().setAtivo(true);
        customer.getUsuario().setDataCadastro(new Date());
        customer.getUsuario().setSenha(EncryptData.encryptSHA256(customer.getUsuario().getSenha()));      
        
        if(clienteRepository.save(customer)){
            return true;
        }
        return false;
    }

    @Override
    @Transacional
    public boolean update(Cliente customer) {
        customerNotification.sendNotification(customer, NotificationType.UPDATE_ACCOUNT, servletRequest);
        customer.getUsuario().setSenha(EncryptData.encryptSHA256(customer.getUsuario().getSenha()));
        return clienteRepository.update(customer);
    }


    @Override
    @Transacional
    public List<Cliente> listAll() {
        return clienteRepository.listAll();
    }

    @Override
    @Transacional
    public List<Cliente> findBy(String param, String atribute) {
        return clienteRepository.findBy(param, atribute);
    }

    @Override
    @Transacional
    public Cliente find(Long id) {
       return clienteRepository.find(id);
    }

    @Override
    public Long getQuantityOfClients() {
        return clienteRepository.getQuantityOfClients();
    }

    @Override
    public boolean isValidCpf(String cpf) {
        return CPFValidator.isValidCPF(cpf);
    }

    @Override
    public boolean isCpfAlreadyInUse(String cpf) {
        return clienteRepository.isCpfAlreadyInUse(CPFValidator.retirarMascara(cpf));
    }

    @Override
    public boolean isValidCnpj(String cnpj) {
        return CNPJValidator.isValidCNPJ(cnpj);
    }

    @Override
    public boolean isCnpjAlreadyInUse(String cnpj) {
        return clienteRepository.isCnpjAlreadyInUse(CNPJValidator.retirarMascara(cnpj));
    }

    @Override
    public boolean isValidAge(Date birthday) {
        return AgeValidator.isValidAge(birthday);
    }
}
