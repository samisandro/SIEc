/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service;

import br.com.siec.model.persistence.dao.IGenericDAO;
import br.com.siec.model.persistence.daoImpl.GenericDAOImpl;
import br.com.siec.util.factory.AbstractFactory;
import br.com.siec.util.factory.ClassType;
import br.com.siec.util.factory.dao.DAOFactory;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.00 24 May 2013
 * @author Josimar Alves
 * @param T
 */
public abstract class Service<T> implements IService<T> {

    protected IGenericDAO dao;

    public Service(IGenericDAO dao) {
        this.dao = dao;
    }

    @Override
    public boolean save(T t) {
        return dao.salve(t);
    }

    @Override
    public boolean update(T t) {
        return (dao.update(t));
    }

    @Override
    public T findById(long id) {
        Object t = dao.find(id);
        if (t != null) {
            return (T) t;
        } else {
            return null;
        }
    }

    @Override
    public List<T> listAll() {
        List<T> results = dao.listAll();
        if (!results.isEmpty()) {
            return results;
        } else {
            return null;
        }
    }

    @Override
    public List<T> findBy(String param, String atribute) {
        List<T> results = dao.findBy(param, atribute);
        if (!results.isEmpty()) {
            return results;
        } else {
            return null;
        }
    }

    @Override
    public T validate(T t) {
        Object result = dao.validate(t);
        if (result != null) {
            return (T) result;
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(T t) {
        return dao.delete(t);
    }
}
