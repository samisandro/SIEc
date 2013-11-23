/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.repository;

import br.com.siec.model.persistence.entity.Cliente;
import java.util.List;

/**
 *
 * @author Josimar
 */
public interface Clientes {
    
    public boolean save(Cliente cliente);

    public Cliente find(Long id);

    public List<Cliente> findBy(String param, String attribute);

    public boolean update(Cliente cliente);

    public List<Cliente> listAll();
    
    public Long getQuantityOfClients();
    
    public boolean isCpfAlreadyInUse(String cpf);
    
    public boolean isCnpjAlreadyInUse(String cpf);
    
}
