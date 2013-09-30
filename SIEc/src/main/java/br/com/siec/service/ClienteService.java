/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service;


import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.model.repository.Clientes;
import java.util.Date;

/**
 * <b>ClienteService</b>
 * @version 1.0.0 August 10, 2013.
 * @author Josimar Alves
 */
public interface ClienteService extends Clientes{
    
    public Cliente create(String classType);
    
    public boolean isValidCpf(String cpf);
        
    public boolean isValidCnpj(String cnpj);   
    
    public boolean isValidBirthday(Date birthday);
    
}
