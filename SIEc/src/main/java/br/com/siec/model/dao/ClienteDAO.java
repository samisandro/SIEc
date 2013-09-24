/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.dao;

import br.com.siec.model.dao.core.DAO;

import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.model.repository.Clientes;

/**
 *
 * @author Josimar
 */
public class ClienteDAO
        extends DAO<Cliente> implements Clientes {

    @Override
    public Long getQuantityOfClients() {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{numberOfClients()} Verificando quantidade de Clientes cadastrados.");
            }

            long quantityofClients = super.getEntityManager()
                    .createQuery("SELECT COUNT(c) FROM Cliente c ", Long.class).getSingleResult();

            return quantityofClients;

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{numberOfClients() --> erro } Ocorreu uma problema ao verificar a quantidade de Clientes cadastrados. -> Exception: " + e);
            }
            return null;
        }
    }
}
