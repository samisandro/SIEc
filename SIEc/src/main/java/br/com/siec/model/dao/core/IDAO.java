package br.com.siec.model.dao.core;

import java.util.List;

public interface IDAO<T> {

    public boolean save(T t);

    public boolean delete(T t);

    public T find(Long id);

    public List<T> findBy(String param, String attribute);

    public boolean update(T t);

    public List<T> listAll();
    
    public T validate(T t);
    
}
