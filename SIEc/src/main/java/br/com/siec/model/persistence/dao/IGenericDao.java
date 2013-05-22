package br.com.siec.model.persistence.dao;

import java.util.List;

public interface IGenericDao<T> {

    public boolean salve(T t);

    public boolean delete(T t);

    public T find(Object id);

    public List<T> findBy(String param, String attribute);

    public boolean update(T t);

    public List<T> listAll();
}
