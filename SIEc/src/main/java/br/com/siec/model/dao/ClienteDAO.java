/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.dao;

import br.com.siec.model.dao.core.DAO;

import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.model.persistence.entity.Pf;
import br.com.siec.model.persistence.entity.Pj;
import br.com.siec.model.persistence.interfaces.IEndereco;
import br.com.siec.model.persistence.interfaces.ITelefone;
import br.com.siec.model.repository.Clientes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Josimar
 */
public class ClienteDAO
        extends DAO<Cliente> implements Clientes {

    @Override
    public boolean save(Cliente cliente) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{save(Cliente cliente)} Salvando Cliente");
            }
            List<ITelefone> telefones = new ArrayList<ITelefone>();
            for (ITelefone phone : cliente.getUsuario().getPessoa().getTelefones()) {
                telefones.add(super.validate(phone));
            }
            cliente.getUsuario().getPessoa().addTelefones(telefones);

            List<IEndereco> enderecos = new ArrayList<IEndereco>();
            for (IEndereco end : cliente.getUsuario().getPessoa().getEnderecos()) {
                enderecos.add(super.validate(end));
            }

            cliente.getUsuario().getPessoa().addEnderecos(enderecos);
            super.getEntityManager().persist(cliente.getUsuario());
            super.getEntityManager().flush();
            super.getEntityManager().persist(cliente);

            return true;

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{save(Cliente cliente) --> erro } Ocorreu um problema ao tentar salvar o Cliente. -> Exception: " + e);
            }
            return false;
        }
    }
    
    @Override
    public boolean update(Cliente cliente) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{update(Cliente cliente)} Atualizando Cliente");
            }
            List<ITelefone> telefones = new ArrayList<ITelefone>();
            for (ITelefone phone : cliente.getUsuario().getPessoa().getTelefones()) {
                telefones.add(super.validate(phone));
            }
            cliente.getUsuario().getPessoa().addTelefones(telefones);

            List<IEndereco> enderecos = new ArrayList<IEndereco>();
            for (IEndereco end : cliente.getUsuario().getPessoa().getEnderecos()) {
                enderecos.add(super.validate(end));
            }            
            
            super.getEntityManager().flush();

            cliente.getUsuario().getPessoa().addEnderecos(enderecos);
            super.getEntityManager().merge(cliente);

            return true;

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{update(Cliente cliente) --> erro } Ocorreu um problema ao tentar atualizar o Cliente. -> Exception: " + e);
            }
            return false;
        }
    }

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

    @Override
    public boolean isCpfAlreadyInUse(String cpf) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{isCpfAlreadyInUse(String cpf)} Validando unicidade do CPF: [ " + cpf + " ]");
            }

            Query query = super.getEntityManager().createQuery(getQueryCPF(), Pf.class);
            query.setParameter("cpf", cpf);

            return query.getSingleResult() != null;

        } catch (NoResultException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{isCPFAlreadyInUse(String cpf)} CPF não está em uso.");
            }
            return false;
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{isCPFAlreadyInUse(String cpf)-->} Ocorreu um problema." + e);
            }
            return false;
        }
    }

    private String getQueryCPF() {
        StringBuilder query = new StringBuilder();
        query.append(" Select pf From Pf pf ")
                .append(" Where pf.cpf = ")
                .append(" :cpf ");

        return query.toString();

    }

    @Override
    public boolean isCnpjAlreadyInUse(String cnpj) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{isCpfAlreadyInUse(String cnpj)} Validando unicidade do CNPJ: [ " + cnpj + " ]");
            }

            Query query = super.getEntityManager().createQuery(getQueryCNPJ(), Pj.class);
            query.setParameter("cnpj", cnpj);

            return query.getSingleResult() != null;

        } catch (NoResultException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{isCnpjAlreadyInUse(String cnpj)} CNPJ não está em uso.");
            }
            return false;
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{isCnpjAlreadyInUse(String cnpj)-->} Ocorreu um problema." + e);
            }
            return false;
        }
    }

    private String getQueryCNPJ() {
        StringBuilder query = new StringBuilder();
        query.append(" Select pj From Pj pj ")
                .append(" Where pj.cnpj = ")
                .append(" :cnpj ");

        return query.toString();

    }

    @Override
    public List<Cliente> findBy(String attribute, String value) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{findBy(String param, String attribute)} Buscando entidade por: [" + attribute + "]" + " - Parametro" + "[" + value + "]");
            }
            Query query = super.getEntityManager().createQuery(getFindByLoginQuery());

            query.setParameter("login", value);
            query.setHint("org.hibernate.cacheable", true);

            return query.getResultList();

        } catch (NoResultException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{findBy(String param, String attribute) Nenhum Cliente Encontrado. ");
            }
            return null;
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{findBy(String param, String attribute) -> Erro} Erro de busca: " + e);
            }
            return null;
        }
    }

    private String getFindByLoginQuery() {
        StringBuilder query = new StringBuilder();
        query.append("Select c From Cliente c ")
                .append(" Where c.usuario.login = ")
                .append(" :login ");

        return query.toString();
    }
}
