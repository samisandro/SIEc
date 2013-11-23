package br.com.siec.service;

import java.util.List;

/**
 * @version 1.00 November 12, 2013
 * @author Josimar Alves
 */
public interface Service<T> {
    
    public T create(String type);
    
    public boolean save(T t); 
    
    public boolean update(T t);
    
    public T find(Long id);
    
    public List<T> listAll();
    
}
