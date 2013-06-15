/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service;

import java.util.List;

/**
 *
 * @author josimar
 */
public interface Service<T> {
    
    public <T> T create(String classType);
    
    public boolean save(T t);

    public boolean update(T t);

    public boolean delete(T t);

    public T findById(long id);

    public List<T> listAll();

    public T validate(T t);

    public List<T> findBy(String param, String atribute);
    
}
