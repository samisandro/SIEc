/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service;


import br.com.siec.model.persistence.entity.Cliente;
import java.util.Date;
import java.util.List;

/**
 * <b>ClienteService</b>
 * @version 1.0.0 August 10, 2013.
 * @author Josimar Alves
 */
public interface ClienteService extends Service<Cliente> {
    
    public boolean isValidCpf(String cpf);
        
    public boolean isValidCnpj(String cnpj);   
    
    public boolean isValidAge(Date birthday);
    
    public List<Cliente> findBy(String param, String atribute);
    
    public Long getQuantityOfClients();
    
    public boolean isCpfAlreadyInUse(String cpf);
    
    public boolean isCnpjAlreadyInUse(String cnpj);
    
}
