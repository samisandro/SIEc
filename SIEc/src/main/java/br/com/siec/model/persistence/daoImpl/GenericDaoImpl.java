package br.com.siec.model.persistence.daoImpl;

import br.com.siec.model.persistence.dao.IGenericDao;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class GenericDaoImpl<T> implements IGenericDao<T> {

    protected EntityManager entityManager;
    private Class<T> typeClass;

    public GenericDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;        
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        typeClass = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public boolean salve(T t) {
        try {
            this.entityManager.persist(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(T t) {
        try {
            this.entityManager.remove(entityManager.merge(t));
            //this.entityManager.remove(this.entityManager.getReference(typeClass, t));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public T find(Object id) {
        try {
            return (T) this.entityManager.find(typeClass, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<T> findBy(String param, String attribute) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(typeClass);
            Root<T> root = criteriaQuery.from(typeClass);
            criteriaQuery.select(root);

            TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
            return (List<T>) query.getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public boolean update(T t) {
        return false;

    }

    @Override
    public List<T> listAll() {
        try {
            return this.entityManager.createQuery("select o from " + typeClass.getSimpleName() + " o").getResultList();
        } catch (Exception e) {
            return null;
        }

    }
}
