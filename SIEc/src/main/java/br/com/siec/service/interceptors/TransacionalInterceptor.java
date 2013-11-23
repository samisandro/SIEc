/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service.interceptors;

import java.io.Serializable;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

/**
 *
 * @author josimar
 */
@Interceptor @Transacional
public class TransacionalInterceptor implements Serializable {
    
    @Inject
    private EntityManager entityManager;
    
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception{
        entityManager.getTransaction().begin();
        
        Object resultado = ctx.proceed();
        
        entityManager.getTransaction().commit();
        
        return resultado;
    }
    
}
